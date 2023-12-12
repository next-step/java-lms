package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.CoverImageRepository;
import nextstep.courses.domain.ImageShape;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImage coverImage, Long sessionId) {
        String sql = "insert into cover_image (session_id, size, image_type, width, height) values (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, sessionId, coverImage.size(), coverImage.imageType().name(), coverImage.imageShape().width(), coverImage.imageShape().height());
    }

    @Override
    public CoverImage findBySessionId(Long sessionId) {
        String sql = "select session_id, size, image_type, width, height from cover_image where session_id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                new ImageShape(rs.getLong(4), rs.getLong(5))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }
}
