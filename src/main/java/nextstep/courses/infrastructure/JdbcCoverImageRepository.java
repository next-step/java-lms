package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.repository.CoverImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CoverImage findById(Long id) {
        String sql = "select * from cover_image where id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
            rs.getLong(1),
            rs.getString(2),
            rs.getInt(3),
            rs.getInt(4),
            rs.getLong(5)
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
