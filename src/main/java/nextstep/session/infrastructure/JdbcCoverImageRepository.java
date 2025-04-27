package nextstep.session.infrastructure;

import nextstep.session.domain.CoverImage;
import nextstep.session.domain.CoverImageRepository;
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
    public int save(CoverImage coverImage) {
        String sql = "insert into cover_image (id, file_name, image_format, file_size, width, height) " +
                "values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                coverImage.getId(), coverImage.getFileName(), coverImage.getImageFormat(), coverImage.getFileSize(),
                coverImage.getWidth(), coverImage.getHeight()
        );
    }

    @Override
    public CoverImage findById(Long id) {
        String sql = "select id, file_name, image_format, file_size, width, height " +
                "from cover_image " +
                "where id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> {
            CoverImage coverImage = new CoverImage.Builder()
                    .id(rs.getLong(1))
                    .fileName(rs.getString(2))
                    .imageFormat(rs.getString(3))
                    .fileSize(rs.getLong(4))
                    .imageSize(rs.getInt(5), rs.getInt(6))
                    .build();
            return coverImage;
        };

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
