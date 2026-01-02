package nextstep.courses.infrastructure;

import nextstep.courses.domain.ImageFile;
import nextstep.courses.repository.ImageFileRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("imageFileRepository")
public class JdbcImageFileRepository implements ImageFileRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcImageFileRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ImageFile save(ImageFile imageFile) {
        String sql = "insert into image_file (session_id, size, image_type, width, height) values (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connect -> {
            PreparedStatement ps = connect.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, imageFile.getSessionId());
            ps.setLong(2, imageFile.getSize());
            ps.setString(3, imageFile.getImageType().toString());
            ps.setInt(4, imageFile.getWidth());
            ps.setInt(5, imageFile.getHeight());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();

        return new ImageFile(id, imageFile.getSessionId(), imageFile.getSize(), imageFile.getImageType().toString(), imageFile.getWidth(), imageFile.getHeight());
    }

    @Override
    public ImageFile findById(long id) {
        String sql = "select * from image_file where id = ?";
        RowMapper<ImageFile> rowMapper = (rs, rowNum) -> new ImageFile(
                rs.getLong("id"),
                rs.getLong("session_id"),
                rs.getLong("size"),
                rs.getString("image_type"),
                rs.getInt("width"),
                rs.getInt("height")
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<ImageFile> findBySessionId(long sessionId) {
        String sql = "select id, session_id, size, image_type, width, height from image_file where session_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new ImageFile(
                        rs.getLong("id"),
                        rs.getLong("session_id"),
                        rs.getLong("size"),
                        rs.getString("image_type"),
                        rs.getInt("width"),
                        rs.getInt("height")
                ), sessionId);
    }
}
