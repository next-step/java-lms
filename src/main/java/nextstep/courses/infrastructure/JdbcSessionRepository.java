package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSessionRepository {
    private JdbcOperations jdbcTemplate;

    public Session findById(Long id) {
        return null;
    }


}
