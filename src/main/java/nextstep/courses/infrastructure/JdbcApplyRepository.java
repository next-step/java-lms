package nextstep.courses.infrastructure;

import nextstep.courses.domain.Apply;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.type.ApplyStatus;
import nextstep.courses.repository.ApplyRepository;
import nextstep.courses.repository.ChargedSessionRepository;
import nextstep.courses.repository.FreeSessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("ApplyRepository")
public class JdbcApplyRepository implements ApplyRepository {
    private JdbcOperations jdbcTemplate;
    private UserRepository userRepository;
    private ChargedSessionRepository chargedSessionRepository;
    private FreeSessionRepository freeSessionRepository;

    public JdbcApplyRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = new JdbcUserRepository(jdbcTemplate);
        this.chargedSessionRepository = new JdbcChargedSessionRepository(jdbcTemplate);
        this.freeSessionRepository = new JdbcFreeSessionRepository(jdbcTemplate);
    }

    @Override
    public int save(Apply apply) {
        String sql = "insert into apply (session_id, user_id, status, created_at) values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            apply.session().id(),
            apply.student().getId(),
            apply.status().name(),
            apply.createdAt());
    }

    @Override
    public Apply findById(Long id) {
        String sql = "select id, session_id, user_id, status, created_at, updated_at from apply where id = ?";
        RowMapper<Apply> rowMapper = (rs, rowNum) -> new Apply(
                rs.getLong(1),
                session(rs.getLong(2)),
                nsUser(rs.getLong(3)),
                ApplyStatus.valueOf(rs.getString(4)),
                toLocalDateTime(rs.getTimestamp(5)),
                toLocalDateTime(rs.getTimestamp(6)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private Session session(Long id) {
        if (chargedSessionRepository.existsById(id)) {
            return chargedSessionRepository.findById(id);
        }
        if (freeSessionRepository.existsById(id)) {
            return freeSessionRepository.findById(id);
        }
        return null;
    }

    private NsUser nsUser(Long id) {
        return userRepository.findById(id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
