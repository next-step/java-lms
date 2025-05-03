package nextstep.session.infrastructure;

import nextstep.session.domain.image.CoverImage;
import nextstep.session.domain.image.CoverImageRepository;
import nextstep.session.domain.image.CoverImages;
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
    public int save(CoverImage coverImage) {
        String sql = "insert into cover_image (id, file_name, image_format, file_size, width, height, session_id) " +
                "values(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                coverImage.getId(), coverImage.getFileName(), coverImage.getImageFormat(), coverImage.getFileSize(),
                coverImage.getWidth(), coverImage.getHeight(), coverImage.getSessionId()
        );
    }

    @Override
    public int save(CoverImages coverImages) {
        if (coverImages == null || coverImages.isEmpty() ) {
            return 0;
        }

        for(CoverImage coverImage : coverImages.images()) {
            save(coverImage);
        }
        return coverImages.size();
    }

    @Override
    public CoverImage findById(Long id) {
        String sql = "select id, file_name, image_format, file_size, width, height, session_id " +
                "from cover_image " +
                "where id = ?";
        RowMapper<CoverImage> rowMapper = getCoverImageRowMapper();

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<CoverImage> findBySessionId(Long sessionId) {
        String sql = "select id, file_name, image_format, file_size, width, height, session_id " +
                "from cover_image " +
                "where session_id = ?";
        RowMapper<CoverImage> rowMapper = getCoverImageRowMapper();

        List<CoverImage> list = jdbcTemplate.query(sql, rowMapper, sessionId);
        return list;
    }

    private static RowMapper<CoverImage> getCoverImageRowMapper() {
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> {
            CoverImage coverImage = new CoverImage.Builder()
                    .id(rs.getLong(1))
                    .fileName(rs.getString(2))
                    .imageFormat(rs.getString(3))
                    .fileSize(rs.getLong(4))
                    .imageSize(rs.getInt(5), rs.getInt(6))
                    .sessionId(rs.getLong(7))
                    .build();
            return coverImage;
        };
        return rowMapper;
    }
}
