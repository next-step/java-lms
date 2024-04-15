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

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session save(Session session) {
        CoverImage coverImage = session.getCoverImage();
        KeyHolder keyHolderForCoverImage = new GeneratedKeyHolder();
        final String sqlForCoverImageSave = "insert into cover_image (name, capacity, width, height, type) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlForCoverImageSave, new String[]{"id"});
            ps.setString(1, coverImage.getName());
            ps.setDouble(2, coverImage.getCapacity());
            ps.setDouble(3, coverImage.getWidth());
            ps.setDouble(4, coverImage.getHeight());
            ps.setString(5, coverImage.getImageType().name());
            return ps;
        }, keyHolderForCoverImage);
        coverImage.updateAsSavedCoverImage(keyHolderForCoverImage.getKey().longValue());

        KeyHolder keyHolderForSession = new GeneratedKeyHolder();
        final String sqlForSessionSave = "insert into session (title, description, max_number_of_enrollment, fee, status, gathering_status, started_at, ended_at, cover_image_id, course_id, created_at) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            ps.setLong(9, session.getIdOfCoverImage());
            ps.setDouble(10, session.getIdOfCourse());
            ps.setTimestamp(11, Timestamp.valueOf(session.getCreatedAt()));
            return ps;
        }, keyHolderForSession);
        session.updateAsSavedSession(keyHolderForSession.getKey().longValue());

        return session;
    }

    @Override
    public Session findById(Long id) {
        try {
            final String sqlForSession = "select S.id, S.title, S.description, S.max_number_of_enrollment, S.fee, S.status, S.gathering_status, S.started_at, S.ended_at" +
                    ", CI.id, CI.name, CI.capacity, CI.width, CI.height, CI.type" +
                    ", C.id, C.title, C.creator_id, C.created_at, C.updated_at" +
                    ", S.created_at, S.updated_at" +
                    " from session as S" +
                    " inner join course as C on C.id = s.course_id" +
                    " inner join cover_image CI on CI.id = S.cover_image_id" +
                    " where S.id = ?";
            RowMapper<Session> rowMapperForSession = (rs, rowNum) -> new Session(
                    rs.getLong(1), rs.getString(2), rs.getString(3), new SessionType(rs.getInt(4), rs.getLong(5)),
                    SessionStatus.valueOf(rs.getString(6)), SessionGatheringStatus.valueOf(rs.getString(7)),
                    new Period(toLocalDateTime(rs.getTimestamp(8)), toLocalDateTime(rs.getTimestamp(9))),
                    new CoverImage(rs.getLong(10), rs.getString(11), rs.getDouble(12), new Dimensions(rs.getDouble(13), rs.getDouble(14)), ImageType.valueOf(rs.getString(15))),
                    new Course(rs.getLong(16), rs.getString(17), rs.getLong(18), toLocalDateTime(rs.getTimestamp(19)), toLocalDateTime(rs.getTimestamp(20))),
                    toLocalDateTime(rs.getTimestamp(21)), toLocalDateTime(rs.getTimestamp(22)));
            Session session = jdbcTemplate.queryForObject(sqlForSession, rowMapperForSession, id);
            session.updateEnrolledUsers(new EnrolledUsers(enrolledUsers(id)));

            return session;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Session update(Session session) {
        final String sqlForSessionForUpdate = "update session set title = ?, description = ?, max_number_of_enrollment = ?, fee = ?, status = ?, gathering_status = ?, started_at = ?, ended_at = ?, cover_image_id = ?, updated_at = ? where id = ?";
        jdbcTemplate.update(sqlForSessionForUpdate, session.getTitle(), session.getDescription(), session.getMaxNumberOfEnrollment(), session.getFee(), session.getSessionStatus().name(), session.getSessionGatheringStatus().name(),
                Timestamp.valueOf(session.getStartedAt()), Timestamp.valueOf(session.getEndedAt()), session.getIdOfCoverImage(), session.getUpdatedAt(), session.getId());

        List<NsUser> notUpdatedEnrolledUsers = enrolledUsers(session.getId());
        session.getEnrolledUsers()
                .stream()
                .filter(user -> !notUpdatedEnrolledUsers.contains(user))
                .forEach(user -> {
                    final String sqlForEnrollmentSave = "insert into enrollment (user_id, session_id, created_at) values (?, ?, ?)";
                    jdbcTemplate.update(sqlForEnrollmentSave, user.getId(), session.getId(), Timestamp.valueOf(LocalDateTime.now()));
                });

        return session;
    }

    private List<NsUser> enrolledUsers(Long id) {
        final String sqlForEnrolledUsers = "select U.id, U.user_id, U.password, U.name, U.email, U.created_at, U.updated_at" +
                " from enrollment as E" +
                " inner join ns_user as U on U.id = E.user_id" +
                " where E.session_id = ?";
        RowMapper<NsUser> rowMapperForEnrolledUser = (rs, rowNum) -> new NsUser(
                rs.getLong(1), rs.getString(2), rs.getString(3),
                rs.getString(4), rs.getString(5), toLocalDateTime(rs.getTimestamp(6)), toLocalDateTime(rs.getTimestamp(7)));

        return jdbcTemplate.query(sqlForEnrolledUsers, rowMapperForEnrolledUser, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
