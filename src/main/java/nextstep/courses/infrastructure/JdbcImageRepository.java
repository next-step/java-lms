package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcImageRepository implements JdbcRepository<CoverImage> {
    private final JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImage image) {
        String sql = "insert into image (url) values(?)";
        return jdbcTemplate.update(sql, image.getUrl());
    }

    @Override
    public CoverImage findById(Long id) {
        String sql = "select id, url from image where id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                rs.getString(2)
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
