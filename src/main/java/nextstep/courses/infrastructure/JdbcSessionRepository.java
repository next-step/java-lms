package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.session.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;
    private final ImageRepository imageRepository;
    private KeyHolder keyHolder = new GeneratedKeyHolder();


    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @Override
    public int save(Long courseId, Session session) {
        final int savedCount = saveSession(courseId, session);

        Long sessionId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        System.out.println("sessionId" + sessionId);
        for (CoverImage coverImage : session.coverImages()) {
            imageRepository.save(sessionId, coverImage);
        }

        return savedCount;
    }

    private int saveSession(final Long courseId, final Session session) {
        final SessionPeriod sessionPeriod = session.sessionPeriod();
        final SessionStatus sessionStatus = session.sessionStatus();
        final SessionType sessionType = session.sessionType();
        final RecruitStatus recruitStatus = session.recruitStatus();

        String sql = "insert into session (title, start_data_time, end_date_time, status, course_id, session_type, " +
                " amount, max_enrollment_count, remain_enrollment_count, recruit, creator_id, created_at) " +
                " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(connection -> {
            EnrollmentCount enrollmentCount = session.enrollmentCount();
            if (sessionType.isFree()) {
                enrollmentCount = new EnrollmentCount(0);
            }

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, session.title());
            ps.setTimestamp(2, Timestamp.valueOf(sessionPeriod.startDateTime()));
            ps.setTimestamp(3, Timestamp.valueOf(sessionPeriod.endDateTime()));
            ps.setString(4, sessionStatus.name());
            ps.setLong(5, courseId);
            ps.setString(6, sessionType.name());
            ps.setLong(7, session.amount().amount());
            ps.setInt(8, enrollmentCount.maxEnrollmentCount());
            ps.setInt(9, enrollmentCount.remainEnrollmentCount());
            ps.setString(10, recruitStatus.name());
            ps.setLong(11, session.creatorId());
            ps.setTimestamp(12, Timestamp.valueOf(session.createdAt()));
            return ps;
        }, keyHolder);
    }

    @Override
    public Optional<Session> findById(final Long id) {
        String sql = "select id, title, start_data_time, end_date_time, status, session_type," +
                " amount, max_enrollment_count, remain_enrollment_count, recruit, creator_id, created_at, updated_at " +
                " from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(3)), toLocalDateTime(rs.getTimestamp(4))),
                SessionStatus.valueOf(rs.getString(5)),
                findByCoverImages(rs.getLong(1)),
                SessionType.valueOf(rs.getString(6)),
                Amount.of(rs.getLong(7)),
                new EnrollmentCount(rs.getInt(8), rs.getInt(9)),
                RecruitStatus.valueOf(rs.getString(10)),
                rs.getLong(11),
                toLocalDateTime(rs.getTimestamp(12)),
                toLocalDateTime(rs.getTimestamp(13)));
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    private List<CoverImage> findByCoverImages(final Long id) {
        return imageRepository.findAllBySessionId(id);
    }
}
