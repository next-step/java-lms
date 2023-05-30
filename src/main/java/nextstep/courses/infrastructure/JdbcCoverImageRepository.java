package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.CoverImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

public class JdbcCoverImageRepository implements CoverImageRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate1) {
        this.jdbcTemplate = jdbcTemplate1;
    }

    @Override
    public int save(CoverImage coverImage) {
        String sql = "insert into cover_image (image_path) values(?)";
        return jdbcTemplate.update(sql, coverImage.imagePath());
    }

    @Override
    public CoverImage findById(Long id) {
        String sql = "select id, image_path from cover_image where id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> CoverImage.of(
            rs.getLong(1),
            rs.getString(2)
        );
        return jdbcTemplate.query(sql, rowMapper, id).stream()
            .findAny()
            .orElse(CoverImage.of(""));
    }

    @Override
    public int update(CoverImage coverImage) {
        String sql = "update cover_image set image_path = ? where id = ?";
        return jdbcTemplate.update(sql, coverImage.imagePath(), coverImage.id());
    }

    @Override
    public int delete(CoverImage image) {
        String sql = "delete from cover_image where id = ?";
        return jdbcTemplate.update(sql, image.id());
    }
}
