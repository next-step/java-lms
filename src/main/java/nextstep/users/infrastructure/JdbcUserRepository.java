package nextstep.users.infrastructure;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository("userRepository")
public class JdbcUserRepository implements UserRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcUserRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(NsUser nsUser) {
        String sql = "INSERT INTO ns_user (user_id, password, name, email, created_at, type) VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, nsUser.getUserId());
            ps.setString(2, nsUser.getPassword());
            ps.setString(3, nsUser.getName());
            ps.setString(4, nsUser.getEmail());
            ps.setTimestamp(5, Timestamp.valueOf(nsUser.getCreatedAt()));
            ps.setString(6, nsUser.getType());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public NsUser findByUserId(String userId) {
        String sql = "select id, user_id, password, name, email, created_at, updated_at, type from ns_user where user_id = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
            rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            toLocalDateTime(rs.getTimestamp(6)),
            toLocalDateTime(rs.getTimestamp(7)),
            rs.getString(8)
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, userId);
    }

    @Override
    public List<NsUser> findByUserIds(List<String> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return Collections.emptyList();
        }

        String inSql = String.join(",", Collections.nCopies(userIds.size(), "?"));

        String sql = String.format(
            "SELECT id, user_id, password, name, email, created_at, updated_at, type FROM ns_user WHERE user_id IN (%s)",
            inSql
        );

        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
            rs.getString("id"),
            rs.getString("user_id"),
            rs.getString("password"),
            rs.getString("name"),
            rs.getString("email"),
            toLocalDateTime(rs.getTimestamp("created_at")),
            toLocalDateTime(rs.getTimestamp("updated_at")),
            rs.getString("type")
        );

        return jdbcTemplate.query(sql, rowMapper, userIds.toArray());
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
