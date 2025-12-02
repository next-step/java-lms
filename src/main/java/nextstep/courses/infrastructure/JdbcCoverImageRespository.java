package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.CoverImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("coverImageRepository")
public class JdbcCoverImageRespository implements CoverImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageRespository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
