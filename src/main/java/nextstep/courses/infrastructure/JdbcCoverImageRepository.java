package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.CoverImageRepository;
import nextstep.courses.domain.session.ImageType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(final CoverImage coverImage, final Long sessionId) {
        String sql = "insert into cover_image (file_size, width ,height, image_type, session_id) values (?, ? ,?, ?, ?)";
        return jdbcTemplate.update(sql, coverImage.getFileSize(), coverImage.getWidth(), coverImage.getHeight(), coverImage.getImageTypeString(), sessionId);
    }

    @Override
    public List<CoverImage> findBySessionId(final Long sessionId) {
        String sql = "select id, file_size, image_type , width, height,  from cover_image where session_id =?";

        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                rs.getInt(2),
                ImageType.valueOf(rs.getString(3)),
                rs.getInt(4),
                rs.getInt(5));

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
