package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.coverImage.CoverImage;
import nextstep.courses.domain.coverImage.Dimensions;
import nextstep.courses.domain.coverImage.ImageType;
import nextstep.courses.domain.session.*;
import nextstep.users.domain.NsUser;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;
    private EnrollmentRepository enrollmentRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, EnrollmentRepository enrollmentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Session save(Session session) {
        KeyHolder keyHolderForSession = new GeneratedKeyHolder();
        final String sqlForSessionSave = "insert into session (title, description, max_number_of_enrollment, fee, status, gathering_status, started_at, ended_at, course_id, created_at) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlForSessionSave, new String[]{"id"});
            ps.setString(1, session.getTitle());
            ps.setString(2, session.getDescription());
            ps.setInt(3, session.getMaxNumberOfEnrollment());
            ps.setLong(4, session.getFee());
            ps.setString(5, session.getSessionStatus().name());
            ps.setString(6, session.getSessionGatheringStatus().name());
            ps.setTimestamp(7, Timestamp.valueOf(session.getStartedAt()));
            ps.setTimestamp(8, Timestamp.valueOf(session.getEndedAt()));
            ps.setDouble(9, session.getIdOfCourse());
            ps.setTimestamp(10, Timestamp.valueOf(session.getCreatedAt()));
            return ps;
        }, keyHolderForSession);
        session.updateAsSavedSession(keyHolderForSession.getKey().longValue());

        final String sqlForSessionCoverImageSave = "insert into session_cover_image (session_id, cover_image_id, created_at) values (?, ?, ?)";
        session.getCoverImages()
                .stream()
                .forEach(coverImage -> {
                    jdbcTemplate.update(sqlForSessionCoverImageSave, session.getId(), coverImage.getId(), Timestamp.valueOf(LocalDateTime.now()));
                });

        return session;
    }

    @Override
    public Session findById(Long id) {
        try {
            final String sqlForSession = "select S.id, S.title, S.description, S.max_number_of_enrollment, S.fee, S.status, S.gathering_status, S.started_at, S.ended_at" +
                    ", C.id, C.title, C.creator_id, C.created_at, C.updated_at" +
                    ", S.created_at, S.updated_at" +
                    " from session as S" +
                    " inner join course as C on C.id = s.course_id" +
                    " where S.id = ?";
            RowMapper<Session> rowMapperForSession = (rs, rowNum) -> new Session(
                    rs.getLong(1), rs.getString(2), rs.getString(3), new SessionType(rs.getInt(4), rs.getLong(5)),
                    SessionStatus.valueOf(rs.getString(6)), SessionGatheringStatus.valueOf(rs.getString(7)),
                    new Period(toLocalDateTime(rs.getTimestamp(8)), toLocalDateTime(rs.getTimestamp(9))),
                    new Course(rs.getLong(10), rs.getString(11), rs.getLong(12), toLocalDateTime(rs.getTimestamp(13)), toLocalDateTime(rs.getTimestamp(14))),
                    toLocalDateTime(rs.getTimestamp(15)), toLocalDateTime(rs.getTimestamp(16)));
            Session session = jdbcTemplate.queryForObject(sqlForSession, rowMapperForSession, id);

            session.updateCoverImages(new CoverImages(coverImages(id)));
            session.updateEnrollments(new Enrollments(enrollments(session)));

            return session;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private List<CoverImage> coverImages(Long id) {
        final String sqlForCoverImages = "select CI.id, CI.name, CI.capacity, CI.width, CI.height, CI.type" +
                " from session_cover_image as SCI" +
                " inner join cover_image as CI on CI.id = SCI.cover_image_id" +
                " where SCI.session_id = ?";

        RowMapper<CoverImage> rowMapperForCoverImage = (rs, rowNum) -> new CoverImage(
                rs.getLong(1), rs.getString(2), rs.getDouble(3), new Dimensions(rs.getDouble(4), rs.getDouble(5)), ImageType.valueOf(rs.getString(6)));

        return jdbcTemplate.query(sqlForCoverImages, rowMapperForCoverImage, id);
    }

    @Override
    public Session update(Session session) {
        final String sqlForSessionForUpdate = "update session set title = ?, description = ?, max_number_of_enrollment = ?, fee = ?, status = ?, gathering_status = ?, started_at = ?, ended_at = ?,  updated_at = ? where id = ?";
        jdbcTemplate.update(sqlForSessionForUpdate, session.getTitle(), session.getDescription(), session.getMaxNumberOfEnrollment(), session.getFee(), session.getSessionStatus().name(), session.getSessionGatheringStatus().name(),
                Timestamp.valueOf(session.getStartedAt()), Timestamp.valueOf(session.getEndedAt()), session.getUpdatedAt(), session.getId());

        List<NsUser> notUpdatedEnrolledUsers = enrollments(session).stream()
                .map(Enrollment::getEnrolledUser)
                .collect(Collectors.toList());

        List<Enrollment> enrollments = session.getEnrollments()
                .stream()
                .map(enrollment -> notUpdatedEnrolledUsers.contains(enrollment.getEnrolledUser()) ? enrollment : enrollmentRepository.save(enrollment))
                .collect(Collectors.toList());
        session.updateEnrollments(new Enrollments(enrollments));

        return session;
    }

    private List<Enrollment> enrollments(Session session) {
        final String sqlForEnrollments = "select E.id, U.id, U.user_id, U.password, U.name, U.email, U.created_at, U.updated_at, E.status, E.created_at, E.updated_at" +
                " from enrollment as E" +
                " inner join ns_user as U on U.id = E.user_id" +
                " where E.session_id = ?";

        RowMapper<Enrollment> rowMapperForEnrollment = (rs, rowNum) -> new Enrollment(
                rs.getLong(1), session,
                new NsUser(rs.getLong(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), toLocalDateTime(rs.getTimestamp(7)), toLocalDateTime(rs.getTimestamp(8))),
                EnrollmentStatus.valueOf(rs.getString(9)), toLocalDateTime(rs.getTimestamp(10)), toLocalDateTime(rs.getTimestamp(11)));

        return jdbcTemplate.query(sqlForEnrollments, rowMapperForEnrollment, session.getId());
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
