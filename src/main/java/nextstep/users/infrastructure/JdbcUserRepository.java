package nextstep.users.infrastructure;

import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public class JdbcUserRepository implements UserRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcUserRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<NsUser> findByUserId(String userId) {
        String sql = "select id, user_id, password, name, email, created_at, updated_at from ns_user where user_id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, getNsUserRowMapper(), userId));
    }

    @Override
    public Optional<NsUser> findById(Long id) {
        String sql = "SELECT id, user_id, password, name, email, created_at, updated_at FROM ns_user WHERE id = ?";

        return Optional.of(jdbcTemplate.queryForObject(sql, getNsUserRowMapper(), id));
    }

    @Override
    public List<NsUser> findByIds(List<Long> ids) {
        String sql = "SELECT id, user_id, password, name, email, created_at, updated_at FROM ns_user WHERE id in (%s)";
        String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));
        return jdbcTemplate.query(String.format(sql, inSql), ids.toArray(), getNsUserRowMapper());
    }

    private RowMapper<NsUser> getNsUserRowMapper() {
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));
        return rowMapper;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    @Override
    public int save(NsUser nsUser) {
        String sql = "INSERT INTO ns_user (id, user_id, password, name, email, created_at) VALUES(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, ps -> {
            ps.setLong(1, nsUser.getId());
            ps.setString(2, nsUser.getUserId());
            ps.setString(3, nsUser.getPassword());
            ps.setString(4, nsUser.getName());
            ps.setString(5, nsUser.getEmail());
            ps.setTimestamp(6, Timestamp.valueOf(nsUser.getCreatedAt()));
        });
    }

    @Override
    public int update(NsUser nsUser) {
        String sql = "UPDATE ns_user SET name = ?, email = ?, updated_at = ? WHERE id = ?";
        return jdbcTemplate.update(sql, ps -> {
            ps.setString(1, nsUser.getName());
            ps.setString(2, nsUser.getEmail());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(4, nsUser.getId());
        });
    }
}
