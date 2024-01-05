package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.ImageShape;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("coverImageDAO")
public class JdbcCoverImageDAO implements CoverImageDAO {
    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageDAO(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void saveAll(List<CoverImage> coverImages) {
        String sql = "insert into cover_image (session_id, size, image_type, width, height) values (?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, coverImages, 100,
                (PreparedStatement ps, CoverImage coverImage) -> {
                    ps.setLong(1, coverImage.sessionId());
                    ps.setLong(2, coverImage.size());
                    ps.setString(3, coverImage.imageType().name());
                    ps.setLong(4, coverImage.imageShape().width());
                    ps.setLong(5, coverImage.imageShape().height());
                });
    }

    @Override
    public List<CoverImage> findBySessionId(Long sessionId) {
        String sql = "select id, session_id, size, image_type, width, height from cover_image where session_id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getString(4),
                new ImageShape(rs.getLong(5), rs.getLong(6))
        );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
