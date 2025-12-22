package nextstep.courses.infrastructure;

import nextstep.courses.domain.ImageFile;
import nextstep.courses.repository.ImageFileRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("imageFileRepository")
public class JdbcImageFileRepository implements ImageFileRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcImageFileRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(ImageFile imageFile) {
        String sql = "insert into image_file (size, image_type, width, height) values (?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                imageFile.getSize(),
                imageFile.getImageType().toString(),
                imageFile.getWidth(),
                imageFile.getHeight()
        );

    }

    @Override
    public ImageFile findById(long id) {
        String sql = "select * from image_file where id = ?";
        RowMapper<ImageFile> rowMapper = (rs, rowNum) -> new ImageFile(
                rs.getLong("id"),
                rs.getLong("size"),
                rs.getString("image_type"),
                rs.getInt("width"),
                rs.getInt("height")
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
