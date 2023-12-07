package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.session.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("paidSessionRepository")
public class JdbcPaidSessionRepository implements PaidSessionRepository {
    private JdbcOperations jdbcTemplate;
    private final ImageRepository imageRepository;

    public JdbcPaidSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @Override
    public int save(Long courseId, PaidSession paidSession) {
        SessionPeriod sessionPeriod = paidSession.sessionPeriod();
        CoverImage coverImage = paidSession.coverImage();
        SessionStatus sessionStatus = paidSession.sessionStatus();
        EnrollmentCount enrollmentCount = paidSession.enrollmentCount();

        String sql = "insert into session (title, start_data_time, end_date_time, status, course_id, image_id, " +
                " amount, max_enrollment_count, remain_enrollment_count ,created_at) " +
                " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, paidSession.title(), sessionPeriod.startDateTime(), sessionPeriod.endDateTime(),
                sessionStatus.name(), courseId, coverImage.id(), paidSession.amount().amount(),
                enrollmentCount.maxEnrollmentCount(), enrollmentCount.remainEnrollmentCount() ,paidSession.createdAt());
    }

    @Override
    public PaidSession findById(final Long id) {
        String sql = "select id, title, start_data_time, end_date_time, status, course_id, image_id," +
                " amount, max_enrollment_count, remain_enrollment_count, created_at, updated_at from session where id = ?";
        RowMapper<PaidSession> rowMapper = (rs, rowNum) -> new PaidSession(
                rs.getLong(1),
                rs.getString(2),
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(3)), toLocalDateTime(rs.getTimestamp(4))),
                SessionStatus.valueOf(rs.getString(5)),
                findByCoverImage(rs.getLong(7)),
                new Amount(rs.getLong(8)),
                new EnrollmentCount(rs.getInt(9), rs.getInt(10)),
                toLocalDateTime(rs.getTimestamp(11)),
                toLocalDateTime(rs.getTimestamp(12)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    private CoverImage findByCoverImage(final Long id) {
        return imageRepository.findById(id);
    }
}
