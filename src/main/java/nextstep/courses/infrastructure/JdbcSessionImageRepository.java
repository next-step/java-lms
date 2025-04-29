package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.entity.SessionImageEntity;
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

@Repository("sessionImageRepository")
public class JdbcSessionImageRepository implements SessionImageRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(SessionImageEntity sessionImageEntity) {
        String sql = "INSERT INTO session_image (" +
            "created_at, updated_at, deleted, image_url, image_type, session_id" +
            ") VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setTimestamp(1, toTimestamp(sessionImageEntity.getCreatedAt()));
            ps.setTimestamp(2, toTimestamp(sessionImageEntity.getUpdatedAt()));
            ps.setBoolean(3, sessionImageEntity.isDeleted());
            ps.setString(4, sessionImageEntity.getImageUrl());
            ps.setString(5, sessionImageEntity.getImageType());
            ps.setLong(6, sessionImageEntity.getSessionId());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public SessionImageEntity findById(Long sessionImageId) {
        String sql = "SELECT id, created_at, updated_at, deleted, image_url, image_type, session_id " +
            "FROM session_image WHERE id = ? AND deleted = false";
        RowMapper<SessionImageEntity> rowMapper = (rs, rowNum) -> SessionImageEntity.builder()
            .id(rs.getLong("id"))
            .createdAt(toLocalDateTime(rs.getTimestamp("created_at")))
            .updatedAt(toLocalDateTime(rs.getTimestamp("updated_at")))
            .deleted(rs.getBoolean("deleted"))
            .imageUrl(rs.getString("image_url"))
            .imageType(rs.getString("image_type"))
            .sessionId(rs.getLong("session_id"))
            .build();

        return jdbcTemplate.queryForObject(sql, rowMapper, sessionImageId);
    }

    @Override
    public List<SessionImageEntity> findAllBySessionId(Long sessionId) {
        String sql = "SELECT id, created_at, updated_at, deleted, image_url, image_type, session_id " +
            "FROM session_image WHERE session_id = ? AND deleted = false";

        return jdbcTemplate.query(sql, new Object[]{sessionId}, (rs, rowNum) ->
            SessionImageEntity.builder()
                .id(rs.getLong("id"))
                .createdAt(toLocalDateTime(rs.getTimestamp("created_at")))
                .updatedAt(toLocalDateTime(rs.getTimestamp("updated_at")))
                .deleted(rs.getBoolean("deleted"))
                .imageUrl(rs.getString("image_url"))
                .imageType(rs.getString("image_type"))
                .sessionId(rs.getLong("session_id"))
                .build()
        );
    }

    @Override
    public void delete(Long sessionImageId) {
        String sql = "UPDATE session_image SET deleted = ?, updated_at = ? WHERE id = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(3, sessionImageId);
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
