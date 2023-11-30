package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.CoverImageFileName;
import nextstep.courses.domain.CoverImagePixel;
import nextstep.courses.domain.CoverImageRepository;
import nextstep.courses.domain.CoverImageSize;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("CoverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImage coverImage) {
        String sql = "INSERT INTO cover_image (session_id, file_name, image_size, width, height) VALUES (?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                coverImage.sessionId(),
                coverImage.fileName(),
                coverImage.fileSize(),
                coverImage.width(),
                coverImage.height());
    }

    @Override
    public CoverImage findById(Long id) {
        String sql = "SELECT id, session_id, file_name, image_size, width, height FROM cover_image WHERE id = ?";
        RowMapper<CoverImage> rowMapper = (resultSet, rowNumber) -> new CoverImage(
                resultSet.getLong(1),
                resultSet.getLong(2),
                new CoverImageFileName(resultSet.getString(3)),
                new CoverImageSize(resultSet.getInt(4)),
                new CoverImagePixel(resultSet.getInt(5), resultSet.getInt(6))
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public int update(CoverImage coverImage) {
        String sql = "UPDATE cover_image SET file_name  = ?, image_size = ?, width = ?, height = ? WHERE id = ?";

        return jdbcTemplate.update(
                sql,
                coverImage.fileName(),
                coverImage.fileSize(),
                coverImage.width(),
                coverImage.height(),
                coverImage.id());
    }

    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM cover_image WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }
}
