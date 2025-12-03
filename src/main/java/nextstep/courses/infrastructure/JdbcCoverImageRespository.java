package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.CoverImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("coverImageRepository")
public class JdbcCoverImageRespository implements CoverImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageRespository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImage coverImage) {
        String sql = "insert into cover_image (size, type, width, height) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                coverImage.getSize(),
                coverImage.getType().toString(),
                coverImage.getWidth(),
                coverImage.getHeight());
    }

    @Override
    public CoverImage findById(Long id) {
        String sql = "select * from cover_image where id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            return new CoverImage(
                    rs.getLong("id"),
                    rs.getLong("size"),
                    rs.getString("type"),
                    rs.getInt("width"),
                    rs.getInt("height")
            );
        }, id);
    }
}
