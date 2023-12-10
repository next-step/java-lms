package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Image.CoverImage;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.SessionProgressState;
import nextstep.courses.repository.CoverImageRepository;
import nextstep.courses.repository.PaidSessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcPaidSessionRepository implements PaidSessionRepository {

    private final JdbcOperations jdbcTemplate;
    private final CoverImageRepository coverImageRepository;

    public JdbcPaidSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Override
    public int save(Course course, PaidSession session) {
        LocalDate startDate = session.getProgressPeriod().getStartDate();
        LocalDate endDate = session.getProgressPeriod().getEndDate();
        String sql = "insert into session (course_id, image_id, start_date, end_date, state, type, fee, max_apply, created_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, course.id(), session.coverImage().id(), startDate, endDate, session.state(), "PAID", session.sessionFee(), session.maxApplyCount(), session.createdAt());
    }

    @Override
    public PaidSession findById(Long id) {
        String sql = "select id, course_id, image_id, start_date, end_date, state, type, fee, max_apply, created_at, updated_at from session where id = ?";
        RowMapper<PaidSession> rowMapper = (rs, rowNum) -> new PaidSession(
                rs.getLong(1),
                findCoverImage(rs.getLong(3)),
                toLocalDate(rs.getDate(4)),
                toLocalDate(rs.getDate(5)),
                SessionProgressState.valueOf(rs.getString(6)),
                rs.getLong(8),
                rs.getInt(9),
                toLocalDateTime(rs.getTimestamp(10)),
                toLocalDateTime(rs.getTimestamp(11))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private CoverImage findCoverImage(Long id) {
        return coverImageRepository.findById(id);
    }

    private LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }


}
