package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.repository.SessionRepository;
import nextstep.courses.record.SessionRecord;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRespository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRespository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionRecord sessionRecord) {
        String sql = "insert into session" +
                " (course_id, cover_image_id, start_date, end_date, max_capacity, tuition, session_type, session_status, created_at)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                sessionRecord.getCourseId(),
                sessionRecord.getCoverImageId(),
                sessionRecord.getStartDate(),
                sessionRecord.getEndDate(),
                sessionRecord.getMaxCapacity(),
                sessionRecord.getTuition(),
                sessionRecord.getSessionType(),
                sessionRecord.getSessionStatus(),
                sessionRecord.getCreatedAt());
    }

    @Override
    public SessionRecord findById(Long id) {
        String sql = "select * from session where id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            return new SessionRecord(
                    rs.getLong("id"),
                    rs.getLong("course_id"),
                    rs.getLong("cover_image_id"),
                    toLocalDateTime(rs.getTimestamp("start_date")),
                    toLocalDateTime(rs.getTimestamp("end_date")),
                    rs.getInt("max_capacity"),
                    rs.getLong("tuition"),
                    rs.getString("session_type"),
                    rs.getString("session_status"),
                    toLocalDateTime(rs.getTimestamp("created_at")),
                    toLocalDateTime(rs.getTimestamp("updated_at"))
            );
        }, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
