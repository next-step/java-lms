package nextstep.courses.infrastructure.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.infrastructure.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionEntityRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionEntityRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionEntity sessionEntity, Long courseId) {
        String sql = "insert into session (session_name, registration_count, max_registration_count, tuition_fee, course_id, progress_state, recruitment_state, fee_type, start_date, end_date, created_at) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            sessionEntity.getSessionName(),
            sessionEntity.getRegistrationCount(),
            sessionEntity.getMaxRegistrationCount(),
            sessionEntity.getTuitionFee(),
            courseId,
            sessionEntity.getProgressState(),
            sessionEntity.getRecruitmentState(),
            sessionEntity.getFeeType(),
            sessionEntity.getStartDate(),
            sessionEntity.getEndDate(),
            sessionEntity.getCreatedAt());
    }

    @Override
    public Optional<SessionEntity> findById(Long id) {
        String sql = "select id, session_name, registration_count, max_registration_count, tuition_fee, course_id, progress_state, recruitment_state, fee_type, start_date, end_date, created_at, updated_at from session where id = ?";
        RowMapper<SessionEntity> rowMapper = (rs, rowNum) -> new SessionEntity(
            rs.getLong(1),
            rs.getString(2),
            rs.getInt(3),
            rs.getInt(4),
            rs.getInt(5),
            rs.getLong(6),
            rs.getString(7),
            rs.getString(8),
            rs.getString(9),
            toLocalDateTime(rs.getTimestamp(10)),
            toLocalDateTime(rs.getTimestamp(11)),
            toLocalDateTime(rs.getTimestamp(12)),
            toLocalDateTime(rs.getTimestamp(13)));

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public int updateRegistrationCount(SessionEntity sessionEntity) {
        String sql = "UPDATE session SET registration_count = ? WHERE id = ?";
        return jdbcTemplate.update(sql, sessionEntity.getRegistrationCount(),
            sessionEntity.getId());
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
