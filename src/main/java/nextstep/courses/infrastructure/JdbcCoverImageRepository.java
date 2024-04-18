package nextstep.courses.infrastructure;

import org.springframework.jdbc.core.JdbcOperations;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.courses.domain.session.image.CoverImageRepository;

public class JdbcCoverImageRepository implements CoverImageRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(final JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(final CoverImage coverImage) {
        final String sql = "insert into cover_image (type, size, width, height) values(?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                coverImage.typeName(),
                coverImage.size(),
                coverImage.width(),
                coverImage.height()
        );
    }
}
