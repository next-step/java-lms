package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Candidate;
import nextstep.courses.repository.CandidateRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.Set;

public class JdbcCandidateRepository implements CandidateRepository {
    private final JdbcOperations jdbcOperations;

    public JdbcCandidateRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Set<Candidate> findBySessionId(Long sessionId) {
        String sql = "select session_id, user_id from session_candidate where session_id = ?";
        RowMapper<Candidate> rowMapper = (rs, rowNum) -> new Candidate(rs.getLong(1), rs.getLong(2));
        return Set.copyOf(jdbcOperations.query(sql, rowMapper, sessionId));
    }
}
