package nextstep.courses.infrastructure;

import nextstep.courses.domain.Apply;
import nextstep.courses.domain.ApplyRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;


@Repository("applyRepository")
public class JdbcApplyRepository implements ApplyRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcApplyRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Apply apply) {

    }
}
