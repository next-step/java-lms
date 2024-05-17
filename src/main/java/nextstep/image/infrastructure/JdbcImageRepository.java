package nextstep.image.infrastructure;

import nextstep.image.domain.Image;
import nextstep.image.domain.ImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ImageRepository")

public class JdbcImageRepository implements ImageRepository {

    private final JdbcOperations jdbcTempleate;

    public JdbcImageRepository(JdbcOperations jdbcTempleate) {
        this.jdbcTempleate = jdbcTempleate;
    }

    @Override
    public int save(Image image) {
        String sql = "INSERT INTO nextstep.image(session_id, width, height, size, file_name, extension, file_path)\n" + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        return jdbcTempleate.update(sql, values(image));
    }

    private PreparedStatementSetter values(Image image) {
        return ps -> {
            ps.setLong(1, image.getSessionId());
            ps.setInt(2, image.getWidth());
            ps.setInt(3, image.getHeight());
            ps.setLong(4, image.getSize());
            ps.setString(5, image.getFileName());
            ps.setString(6, image.getExtension());
            ps.setString(7, image.getFilePath());
        };
    }

    @Override
    public Image findById(Long id) {
        String sql = "SELECT id, session_id, width, height, size, file_name, extension, file_path from image where id = ?";
        return jdbcTempleate.queryForObject(sql, imageRowMapper(), id);
    }

    private RowMapper<Image> imageRowMapper() {
        return (rs, rowNum) -> new Image(
                rs.getLong(1),
                rs.getLong(2),
                rs.getInt(3),
                rs.getInt(4),
                rs.getLong(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8)
        );
    }

    @Override
    public List<Image> findBySessionId(Long sessionId) {
         String sql = "SELECT id, session_id, width, height, size, file_name, extension, file_path from nextstep.image where id = ?";
        return jdbcTempleate.query(sql, imageRowMapper(), sessionId);
    }

    @Override
    public void saveAll(List<Image> images) {
        String sql = "INSERT INTO nextstep.image(session_id, width, height, size, file_name, extension, file_path)\n" + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTempleate.batchUpdate(sql, images, images.size(), values());
    }

    private ParameterizedPreparedStatementSetter<Image> values() {
        return (ps, image)  -> {
            ps.setLong(1, image.getSessionId());
            ps.setInt(2, image.getWidth());
            ps.setInt(3, image.getHeight());
            ps.setLong(4, image.getSize());
            ps.setString(5, image.getFileName());
            ps.setString(6, image.getExtension());
            ps.setString(7, image.getFilePath());
        };
    }
}
