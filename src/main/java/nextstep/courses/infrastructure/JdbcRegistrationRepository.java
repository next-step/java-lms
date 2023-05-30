package nextstep.courses.infrastructure;

import nextstep.courses.domain.Registration;
import nextstep.courses.domain.RegistrationRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("registrationRepository")
public class JdbcRegistrationRepository implements RegistrationRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcRegistrationRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Registration> findBySessionId(Long sessionId) {
        return null;
    }
}
