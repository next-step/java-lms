package nextstep.courses.infrastructure;

import nextstep.courses.domain.ImageFile;
import nextstep.courses.repository.ImageFileRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository("imageFileRepository")
public class JdbcImageFileRepository implements ImageFileRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcImageFileRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(ImageFile imageFile) {
        String sql = "insert into image_file (size, image_type, width, height) values (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connect -> {
            PreparedStatement ps = connect.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, imageFile.getSize());
            ps.setString(2, imageFile.getImageType().toString());
            ps.setInt(3, imageFile.getWidth());
            ps.setInt(4, imageFile.getHeight());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
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
