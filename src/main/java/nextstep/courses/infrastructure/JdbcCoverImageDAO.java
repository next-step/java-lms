package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.session.ImageShape;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository("coverImageDAO")
public class JdbcCoverImageDAO implements CoverImageDAO {
    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageDAO(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(CoverImage coverImage) {
        String sql = "insert into cover_image (session_id, size, image_type, width, height) values (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"session_id"});
            ps.setLong(1, coverImage.sessionId());
            ps.setLong(2, coverImage.size());
            ps.setString(3, coverImage.imageType().name());
            ps.setLong(4, coverImage.imageShape().width());
            ps.setLong(5, coverImage.imageShape().height());
            return ps;
        }, keyHolder);
        return (Long) keyHolder.getKey();
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
