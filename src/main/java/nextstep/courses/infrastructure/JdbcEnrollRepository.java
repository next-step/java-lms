package nextstep.courses.infrastructure;

import nextstep.courses.domain.Enroll;
import nextstep.courses.domain.EnrollId;
import nextstep.courses.domain.EnrollRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("EnrollRepository")
public class JdbcEnrollRepository implements EnrollRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcEnrollRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Enroll save(Enroll enroll) {
        throw new RuntimeException("Not Yet Implemented");
    }

    @Override
    public Optional<Enroll> findById(EnrollId enrollId) {
        throw new RuntimeException("Not Yet Implemented");
    }

    @Override
    public List<Enroll> findAll() {
        throw new RuntimeException("Not Yet Implemented");
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from enroll");
    }
}
