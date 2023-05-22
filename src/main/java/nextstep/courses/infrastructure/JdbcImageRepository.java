package nextstep.courses.infrastructure;

import nextstep.courses.domain.Image;
import nextstep.courses.domain.repository.ImageRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcImageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Image findById(Long id) {
        String sql = "SELECT * FROM image WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, imageRowMapper(), id);
    }

    @Override
    public long save(Image image) {
        String query = "INSERT INTO image (name, url, size, type) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, image.getName());
            ps.setString(2, image.getUri().toString());
            ps.setLong(3, image.getSize());
            ps.setString(4, image.getType().getValue());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public int update(Image image) {
        String query = "UPDATE image SET name = ?, url = ?, size = ?, type = ? WHERE id = ?";
        return jdbcTemplate.update(query, image.getName(), image.getUri().toString(), image.getSize(), image.getType().getValue(), image.getId());
    }

    @Override
    public int delete(Long id) {
        String query = "DELETE FROM image WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }

    private RowMapper<Image> imageRowMapper() {
        return (rs, rowNum) -> new Image(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("url"),
                rs.getLong("size"),
                rs.getString("type")
        );
    }
    
}
