package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.session.apply.Applies;
import nextstep.courses.domain.course.session.apply.Apply;
import nextstep.courses.domain.course.session.apply.ApplyRepository;
import nextstep.courses.domain.course.session.apply.ApprovalStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository("applyRepository")
public class JdbcApplyRepository implements ApplyRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcApplyRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Applies findAllBySessionId(Long sessionId) {
        String sql = "select " +
                "session_id, ns_user_id, approval_status, creator_id, created_at, updated_at " +
                "from apply where session_id = ?";
        RowMapper<Apply> rowMapper = (rs, rowNum) -> new Apply(
                rs.getLong(1),
                rs.getLong(2),
                ApprovalStatus.find(rs.getString(3)),
                rs.getLong(4),
                rs.getTimestamp(5).toLocalDateTime(),
                toLocalDateTime(rs.getTimestamp(6))
        );

        List<Apply> applies = jdbcTemplate.query(sql, rowMapper, sessionId);
        return new Applies(applies);
    }

    @Override
    public Apply save(Apply apply) {
        String sql = "insert into apply " +
                "(session_id, ns_user_id, approval_status, creator_id, created_at, updated_at) " +
                "values(?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, apply.sessionId(), apply.nsUserId(), apply.approval(),
                apply.creatorId(), apply.createdAt(), apply.updatedAt());

        return apply;
    }

    @Override
    public Apply update(Apply apply) {
        String sql = "update apply set approval_status = ? where session_id = ? and ns_user_id = ?";

        jdbcTemplate.update(sql, apply.approval(), apply.sessionId(), apply.nsUserId());

        return apply;
    }

    @Override
    public Optional<Apply> findApplyByNsUserIdAndSessionId(Long nsUserId, Long sessionId) {
        String sql = "select " +
                "session_id, ns_user_id, approval_status, creator_id, created_at, updated_at " +
                "from apply where ns_user_id = ? and session_id = ?";
        RowMapper<Apply> rowMapper = (rs, rowNum) -> new Apply(
                rs.getLong(1),
                rs.getLong(2),
                ApprovalStatus.find(rs.getString(3)),
                rs.getLong(4),
                rs.getTimestamp(5).toLocalDateTime(),
                toLocalDateTime(rs.getTimestamp(6)));
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, nsUserId, sessionId));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
