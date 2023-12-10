package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.ImageSize;
import nextstep.courses.domain.SessionImage;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionImageRepository")
public class JdbcSessionImageRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(SessionImage image) {
        String sql = "insert into session_image (file_size, file_type, image_width, image_height) " +
                "values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, image.getFileSize(), image.getFileType(),
                image.getImageWidth(), image.getImageHeight());
    }


    public SessionImage findById(Long id) {
        String sql = "select file_size, file_type, image_width, image_height from session_image where id = ?";
        RowMapper<SessionImage> rowMapper = (rs, rowNum) -> new SessionImage(
                rs.getInt(1),
                rs.getString(2),
                new ImageSize(rs.getInt(3), rs.getInt(4))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

}
