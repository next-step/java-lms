package nextstep.courses.infrastructure;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionDate;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.enums.ProgressState;
import nextstep.courses.domain.enums.RecruitmentState;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (start_date, end_date, cover_image_path, is_free, progress_state, " +
                "recruitment_state, max_capacity, course_id, created_at)" +
                " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getStartDate(), session.getEndDate(), session.getCoverImagePath(),
                session.isFree(), session.getProgressState(), session.getRecruitmentState(), session.getMaxCapacity(),
                session.getCourseId(), session.getCreatedAt());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, start_date, end_date, cover_image_path, is_free, progress_state, recruitment_state, " +
                "max_capacity, course_id, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                new SessionDate(rs.getString(2), rs.getString(3)),
                rs.getString(4),
                rs.getBoolean(5),
                ProgressState.of(rs.getInt(6)),
                RecruitmentState.of(rs.getInt(7)),
                rs.getInt(8),
                rs.getLong(9),
                toLocalDateTime(rs.getTimestamp(10)),
                toLocalDateTime(rs.getTimestamp(11)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
