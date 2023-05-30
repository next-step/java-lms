package nextstep.lms.infrastructure;

import nextstep.lms.domain.Course;
import nextstep.lms.domain.LmsUser;
import nextstep.lms.domain.LmsUserRole;
import nextstep.lms.repository.LmsUserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("lmsUserRepository")
public class JdbcLmsUserRepository implements LmsUserRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcLmsUserRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<LmsUser> findByUserId(String userId) {
        String sql = "select id, user_id, password, name, role, created_at, updated_at from lms_user where user_id = ?";
        RowMapper<LmsUser> rowMapper = (rs, rowNum) -> new LmsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                LmsUserRole.valueOf(rs.getString(5)),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, userId));
    }

    @Override
    public int save(LmsUser lmsUser) {
        String sql = "insert into lms_user (user_id, password, name, role, created_at) values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, lmsUser.getUserId(), lmsUser.getPassword(), lmsUser.getName(), lmsUser.getRole(), lmsUser.getCreatedAt());
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
