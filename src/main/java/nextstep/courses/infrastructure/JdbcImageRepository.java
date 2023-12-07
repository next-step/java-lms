package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Image;
import nextstep.courses.domain.session.ImageRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Image image, long sessionId) {
        String sql = "insert into image (type, session_id, width, height, file_size) values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, image.type(), sessionId, image.width(), image.height(), image.fileSize());
    }

    @Override
    public Image findBySessionId(Long id) {
        String sql = "select id, type, width, height, file_size from image where id = ?";
        RowMapper<Image> rowMapper = (rs, rowNum) -> Image.of(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                rs.getLong(4),
                rs.getLong(5));

        return preventNull(id, sql, rowMapper);
    }

    private Image preventNull(Long id, String sql, RowMapper<Image> rowMapper) {
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
