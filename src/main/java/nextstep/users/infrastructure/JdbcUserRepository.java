package nextstep.users.infrastructure;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("userRepository")
public class JdbcUserRepository implements UserRepository {
    private JdbcOperations jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcUserRepository(JdbcOperations jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<NsUser> findByUserId(String userId) {
        String sql = "select id, user_id, password, name, email, created_at, updated_at from ns_user where user_id = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, userId));
    }

    @Override
    public List<NsUser> findByIds(List<Long> ids) {
        if (ids.size() < 100) {
            return findByNoPartitionIds(ids);
        }

        return findByPartitionIds(ids);
    }

    private List<NsUser> findByNoPartitionIds(List<Long> ids) {
        String sql = "select id, user_id, password, name, email, created_at, updated_at from ns_user where id IN (:values)";
        MapSqlParameterSource parameters = new MapSqlParameterSource("values", ids);
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));

        return namedParameterJdbcTemplate.query(sql, parameters, rowMapper);
    }

    private List<NsUser> findByPartitionIds(List<Long> ids) {
        List<NsUser> nsUsers = new ArrayList<>();
        Map<Long, List<Long>> groups = ids.stream()
                .collect(Collectors.groupingBy(id -> id / 100));

        List<List<Long>> partitions = new ArrayList<>(groups.values());
        partitions.stream().forEach(partition -> {
            String sql = "select id, user_id, password, name, email, created_at, updated_at from ns_user where id IN (:values)";

            MapSqlParameterSource parameters = new MapSqlParameterSource("values", partition);
            RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    toLocalDateTime(rs.getTimestamp(6)),
                    toLocalDateTime(rs.getTimestamp(7)));

            nsUsers.addAll(namedParameterJdbcTemplate.query(sql, parameters, rowMapper));
        });
        return nsUsers;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
