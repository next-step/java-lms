package nextstep.courses.infrastructure;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.cover.CoverImageRepository;
import nextstep.courses.domain.cover.ImageDimension;
import nextstep.courses.domain.cover.ImageSize;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<CoverImage> findBySessionId(Long sessionId) {
        String sql = "SELECT file_name, image_size, extension, width, height FROM cover_image WHERE session_id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> CoverImage.of(
                rs.getString("file_name"),
                ImageSize.of(rs.getInt("image_size")),
                rs.getString("extension"),
                ImageDimension.of(rs.getInt("width"), rs.getInt("height"))
        );
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, sessionId));
    }

    @Override
    public void save(CoverImage coverImage, Long sessionId) {
        String sql = "INSERT INTO cover_image (session_id, file_name, image_size, extension, width, height) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                sessionId,
                coverImage.getFileName(),
                coverImage.getImageSize(),
                coverImage.getExtension().name(),
                coverImage.getWidth(),
                coverImage.getHeight()
        );
    }
}