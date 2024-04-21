package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.CoverImageRepository;
import nextstep.courses.domain.session.image.CoverImage;
import nextstep.courses.domain.session.image.CoverImages;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {

    private JdbcOperations jdbcTemplate;
    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Long sessionId, CoverImages coverImages) {
        String sql = "INSERT INTO session_cover_image " +
                "(session_id, image_type, image_file_size, " +
                "image_width, image_height)" +
                "VALUES(?,?,?," +
                "?,?)";

        List<CoverImage> pluralCoverImages = coverImages.getCoverImages();

        int count = pluralCoverImages.stream()
                .mapToInt(coverImage -> {
                    jdbcTemplate.update(
                            sql,
                            sessionId,
                            coverImage.getImageType(),
                            coverImage.getImageFileSize(),
                            coverImage.getImageSizeWidth(),
                            coverImage.getImageSizeHeight()
                    );
                    return 1;
                })
                .sum();

        return count;
    }

    @Override
    public List<CoverImage> findBySessionId(Long sessionId) {
        String sql = "SELECT " +
                        "image_id, " +
                        "image_type, " +
                        "image_file_size, " +
                        "image_width, " +
                        "image_height " +
                    "FROM session_cover_image " +
                    "WHERE session_id = ?";

        RowMapper<CoverImage> coverImageRowMapper = coverImageRowMapper();

        return jdbcTemplate.query(sql, coverImageRowMapper, sessionId);
    }

    private RowMapper<CoverImage> coverImageRowMapper() {
        return (rs, rowNum) -> CoverImage.of(
                rs.getLong("image_id"),
                rs.getString("image_type"),
                rs.getInt("image_file_size"),
                rs.getInt("image_width"),
                rs.getInt("image_height")
        );
    }
}
