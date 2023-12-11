package nextstep.courses.infrastructure;

import nextstep.courses.domain.coverImage.CoverImage;
import nextstep.courses.domain.coverImage.CoverImageRepository;
import nextstep.courses.domain.coverImage.CoverImages;
import nextstep.courses.dto.CoverImageDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcCoverImageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(CoverImage coverImage) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into cover_image (path, file_size, image_type, width, height) values (?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql);
            ps.setString(1, coverImage.getPath());
            ps.setInt(2, coverImage.getFileSize());
            ps.setString(3, coverImage.getImageType().name());
            ps.setInt(4, coverImage.getDimensions().getWidth());
            ps.setInt(5, coverImage.getDimensions().getHeight());
            return ps;
        }, keyHolder);

        return (long) keyHolder.getKey();
    }

    @Override
    public CoverImages findBySessionId(Long sessionId) {
        String sql = "select session_id, path, file_size, image_type, width, height from cover_image where session_id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getInt(6)
        );
        List<CoverImage> queryResult = jdbcTemplate.query(sql, rowMapper, sessionId);
        return new CoverImages(queryResult);
    }


}
