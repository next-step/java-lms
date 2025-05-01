package nextstep.payments.infrastructor;

import nextstep.payments.domain.PaymentRepository;
import nextstep.payments.entity.PaymentEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository("paymentRepository")
public class JdbcPaymentRepository implements PaymentRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcPaymentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(PaymentEntity paymentEntity) {
        String sql = "INSERT INTO payment (deleted, created_at, user_id, session_id, amount, status) VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setBoolean(1, paymentEntity.isDeleted());
            ps.setTimestamp(2, Timestamp.valueOf(paymentEntity.getCreatedAt()));
            ps.setString(3, paymentEntity.getUserId());
            ps.setLong(4, paymentEntity.getSessionId());
            ps.setLong(5, paymentEntity.getAmount());
            ps.setString(6, paymentEntity.getStatus());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public PaymentEntity findById(Long paymentId) {
        String sql = "SELECT id, created_at, updated_at, deleted, user_id, session_id, amount, status FROM payment WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{paymentId}, (rs, rowNum) -> PaymentEntity.builder()
            .id(rs.getLong("id"))
            .createdAt(toLocalDateTime(rs.getTimestamp("created_at")))
            .updatedAt(toLocalDateTime(rs.getTimestamp("updated_at")))
            .deleted(rs.getBoolean("deleted"))
            .userId(rs.getString("user_id"))
            .sessionId(rs.getLong("session_id"))
            .amount(rs.getLong("amount"))
            .status(rs.getString("status"))
            .build());
    }

    @Override
    public List<PaymentEntity> findBySession(Long sessionId) {
        String sql = "SELECT id, created_at, updated_at, deleted, user_id, session_id, amount, status FROM payment WHERE session_id = ?";

        return jdbcTemplate.query(sql, ps -> ps.setLong(1, sessionId), (rs, rowNum) -> PaymentEntity.builder()
            .id(rs.getLong("id"))
            .createdAt(toLocalDateTime(rs.getTimestamp("created_at")))
            .updatedAt(toLocalDateTime((rs.getTimestamp("updated_at"))))
            .deleted(rs.getBoolean("deleted"))
            .userId(rs.getString("user_id"))
            .sessionId(rs.getLong("session_id"))
            .amount(rs.getLong("amount"))
            .status(rs.getString("status"))
            .build());
    }

    @Override
    public void updateStatus(Long paymentId, String status) {
        String sql = "UPDATE payment SET status = ?, updated_at = ? WHERE id = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(3, paymentId);
            return ps;
        });
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
