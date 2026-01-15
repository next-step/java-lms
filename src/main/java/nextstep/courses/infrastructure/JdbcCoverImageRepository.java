package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import nextstep.courses.domain.CoverImageRepository;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImageType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcCoverImageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImage coverImage) {
        String sql = "insert into cover_image (session_id, size, type, width, height, created_at) values (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            coverImage.getSessionId(),
            coverImage.getSize(),
            coverImage.getTypeName(),
            coverImage.getWidth(),
            coverImage.getHeight(),
            coverImage.getCreatedAt());
    }

    @Override
    public CoverImage findBySessionId(Long sessionId) {
        String sql = "select id, session_id, size, type, width, height, created_at, updated_at from cover_image where session_id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
            rs.getLong("id"),
            rs.getLong("session_id"),
            rs.getLong("size"),
            ImageType.valueOf(rs.getString("type")),
            rs.getInt("width"),
            rs.getInt("height"),
            toLocalDateTime(rs.getTimestamp("created_at")),
            toLocalDateTime(rs.getTimestamp("updated_at"))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
