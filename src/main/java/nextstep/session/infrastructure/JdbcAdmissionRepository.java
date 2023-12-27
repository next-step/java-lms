package nextstep.session.infrastructure;

import nextstep.session.domain.Admission;
import nextstep.session.domain.AdmissionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

import static nextstep.common.domain.utils.JdbcConvertUtils.toLocalDateTime;

@Repository
public class JdbcAdmissionRepository implements AdmissionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcAdmissionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Admission admission) {
        String sql = "insert into session_admission (created_at, updated_at, user_id, session_id) values(?,?,?,?)";
        jdbcTemplate.update(sql, admission.getCreatedAt(), admission.getUpdatedAt(), admission.getStudentId(), admission.getSessionId());
    }

    @Override
    public List<Admission> findAllBySessionId(Long sessionId) {
        String sql = "select id, created_at, updated_at, user_id, session_id from session_admission where session_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Admission(
                rs.getLong(1),
                toLocalDateTime(rs.getTimestamp(2)),
                toLocalDateTime(rs.getTimestamp(3)),
                rs.getLong(4),
                rs.getLong(5)
        ), sessionId);
    }
}
