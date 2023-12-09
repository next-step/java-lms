package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.session.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;
    private final ImageRepository imageRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @Override
    public int save(Long courseId, Session session) {
        SessionPeriod sessionPeriod = session.sessionPeriod();
        CoverImage coverImage = session.coverImage();
        SessionStatus sessionStatus = session.sessionStatus();
        SessionType sessionType = session.sessionType();

        if (SessionType.FREE.isSame(sessionType)) {
            System.out.println("=====================");
            String sql = "insert into session (title, start_data_time, end_date_time, status, course_id, image_id, type, " +
                    " created_at) " +
                    " values(?, ?, ?, ?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, session.title(), sessionPeriod.startDateTime(), sessionPeriod.endDateTime(),
                    sessionStatus.name(), courseId, coverImage.id(), sessionType.name(), session.createdAt());
        }

        EnrollmentCount enrollmentCount = session.enrollmentCount();
        String sql = "insert into session (title, start_data_time, end_date_time, status, course_id, image_id, type, " +
                " amount, max_enrollment_count, remain_enrollment_count, created_at) " +
                " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.title(), sessionPeriod.startDateTime(), sessionPeriod.endDateTime(),
                sessionStatus.name(), courseId, coverImage.id(), sessionType.name(), session.amount().amount(),
                enrollmentCount.maxEnrollmentCount(), enrollmentCount.remainEnrollmentCount() ,session.createdAt());
    }

    @Override
    public Optional<Session> findById(final Long id) {
        String sql = "select id, title, start_data_time, end_date_time, status, course_id, image_id, type," +
                " amount, max_enrollment_count, remain_enrollment_count, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(3)), toLocalDateTime(rs.getTimestamp(4))),
                SessionStatus.valueOf(rs.getString(5)),
                findByCoverImage(rs.getLong(7)),
                SessionType.valueOf(rs.getString(8)),
                Amount.of(rs.getLong(9)),
                new EnrollmentCount(rs.getInt(10), rs.getInt(11)),
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

    private CoverImage findByCoverImage(final Long id) {
        return imageRepository.findById(id).get();
    }
}
