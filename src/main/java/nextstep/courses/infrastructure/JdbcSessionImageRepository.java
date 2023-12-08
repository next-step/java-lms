package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionImage;
import nextstep.courses.enumeration.ExtensionType;
import nextstep.courses.repository.SessionImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository("sessionImageRepository")
public class JdbcSessionImageRepository implements SessionImageRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionImage sessionImage) {
        String sql = "insert into session_image " +
                "(id, session_id, image_url, extension_type, size, created_at, updated_at) " +
                "values(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                sessionImage.getId(),
                sessionImage.getSessionId(),
                sessionImage.getImageUrl(),
                sessionImage.getExtensionType().name(),
                sessionImage.getSize(),
                sessionImage.getCreatedAt(),
                sessionImage.getUpdatedAt());
    }

    @Override
    public int update(String imageUrl, ExtensionType extensionType, Long size, Long id) {
        String sql = "update session_image set image_url = ?, extension_type = ?, size = ?, updated_at = ? where id = ?";
        return jdbcTemplate.update(sql,
                imageUrl,
                extensionType.name(),
                size,
                LocalDateTime.now(),
                id);
    }

    @Override
    public int delete(Long id) {
        String sql = "delete from session_image where id = ?";
        return jdbcTemplate.update(sql,
                id);
    }

    @Override
    public Optional<SessionImage> findById(Long id) {
        String sql = "select id, session_id, image_url, extension_type, size, created_at, updated_at from session_image where id = ?";
        RowMapper<SessionImage> rowMapper = (rs, rowNum) -> new SessionImage(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                ExtensionType.findType(rs.getString(4)),
                rs.getLong(5),
                rs.getTimestamp(6).toLocalDateTime(),
                rs.getTimestamp(7).toLocalDateTime());

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }
}
