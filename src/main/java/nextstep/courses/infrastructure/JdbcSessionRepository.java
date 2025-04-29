package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.entity.SessionEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(SessionEntity sessionEntity) {
        String sql = "INSERT INTO session ("
            + "created_at, updated_at, deleted, course_id, fee, capacity, "
            + "image_url, image_type, start_date, end_date, type, status, enroll_status"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setTimestamp(1, toTimestamp(sessionEntity.getCreatedAt()));
            ps.setTimestamp(2, toTimestamp(sessionEntity.getUpdatedAt()));
            ps.setBoolean(3, sessionEntity.isDeleted());
            ps.setLong(4, sessionEntity.getCourseId());
            ps.setLong(5, sessionEntity.getFee());
            ps.setInt(6, sessionEntity.getCapacity());
            ps.setString(7, sessionEntity.getImageUrl());
            ps.setString(8, sessionEntity.getImageType());
            ps.setTimestamp(9, toTimestamp(sessionEntity.getStartDate()));
            ps.setTimestamp(10, toTimestamp(sessionEntity.getEndDate()));
            ps.setString(11, sessionEntity.getType());
            ps.setString(12, sessionEntity.getStatus());
            ps.setString(13, sessionEntity.getEnrollStatus());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public SessionEntity findById(Long id) {
        String sessionSql = "SELECT id, created_at, updated_at, deleted, course_id, " +
            "fee, capacity, image_url, image_type, start_date, end_date, type, status, enroll_status " +
            "FROM session WHERE id = ? AND deleted = false";

        RowMapper<SessionEntity> rowMapper = (rs, rowNum) -> SessionEntity.builder()
            .id(rs.getLong("id"))
            .createdAt(toLocalDateTime(rs.getTimestamp("created_at")))
            .updatedAt(toLocalDateTime(rs.getTimestamp("updated_at")))
            .deleted(rs.getBoolean("deleted"))
            .courseId(rs.getLong("course_id"))
            .fee(rs.getLong("fee"))
            .capacity(rs.getInt("capacity"))
            .imageUrl(rs.getString("image_url"))
            .imageType(rs.getString("image_type"))
            .startDate(toLocalDateTime(rs.getTimestamp("start_date")))
            .endDate(toLocalDateTime(rs.getTimestamp("end_date")))
            .type(rs.getString("type"))
            .status(rs.getString("status"))
            .enrollStatus(rs.getString("enroll_status"))
            .build();

        List<SessionEntity> results = jdbcTemplate.query(sessionSql, rowMapper, id);
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<SessionEntity> findAllByCourseId(Long courseId) {
        String sql = "SELECT id, created_at, updated_at, deleted, course_id, " +
            "fee, capacity, image_url, image_type, start_date, end_date, type, status, enroll_status " +
            "FROM session WHERE course_id = ? AND deleted = false";

        RowMapper<SessionEntity> rowMapper = (rs, rowNum) -> SessionEntity.builder()
            .id(rs.getLong("id"))
            .createdAt(toLocalDateTime(rs.getTimestamp("created_at")))
            .updatedAt(toLocalDateTime(rs.getTimestamp("updated_at")))
            .deleted(rs.getBoolean("deleted"))
            .courseId(rs.getLong("course_id"))
            .fee(rs.getLong("fee"))
            .capacity(rs.getInt("capacity"))
            .imageUrl(rs.getString("image_url"))
            .imageType(rs.getString("image_type"))
            .startDate(toLocalDateTime(rs.getTimestamp("start_date")))
            .endDate(toLocalDateTime(rs.getTimestamp("end_date")))
            .type(rs.getString("type"))
            .status(rs.getString("status"))
            .enrollStatus(rs.getString("enroll_status"))
            .build();

        return jdbcTemplate.query(sql, rowMapper, courseId);
    }

    @Override
    public void delete(Long sessionId) {
        String sql = "UPDATE session SET deleted = ?, updated_at = ? WHERE id = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, true); // Set the deleted flag to true
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now())); // Update the updated_at field
            ps.setLong(3, sessionId);
            return ps;
        });
    }

    private Timestamp toTimestamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Timestamp.valueOf(localDateTime);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
