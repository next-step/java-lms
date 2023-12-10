package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.repository.CoverImageRepository;
import nextstep.courses.exception.ImageFileInfoException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<CoverImage> findById(Long id) {
        String sql = "select * from cover_image where id = ?";
        RowMapper<CoverImage> rowMapper = coverImageRowMapper();

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    private RowMapper<CoverImage> coverImageRowMapper() {
        return (rs, rowNum) -> {
            try {
                 return new CoverImage(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getLong(5)
                );
            } catch (ImageFileInfoException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
