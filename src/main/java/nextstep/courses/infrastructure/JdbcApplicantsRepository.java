package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.session.Applicants;
import nextstep.courses.domain.course.session.ApplicantsRepository;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("applicantsRepository")
public class JdbcApplicantsRepository implements ApplicantsRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcApplicantsRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Applicants findAllBySessionId(Long sessionId) {
        List<Long> applicantIds = findAllApplicantIdsBySessionId(sessionId);
        List<NsUser> nsUsers = new ArrayList<>();
        for (Long applicantId : applicantIds) {
            NsUser nsUser = findAllNsUsersById(applicantId).orElseThrow(NotFoundException::new);
            nsUsers.add(nsUser);
        }

        return new Applicants(nsUsers);
    }

    private List<Long> findAllApplicantIdsBySessionId(Long sessionId) {
        String sql = "select ns_user_id from apply where session_id = ?";
        RowMapper<Long> rowMapper = (rs, rowNum) -> rs.getLong(1);
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private Optional<NsUser> findAllNsUsersById(Long applicantId) {
        String sql = "select " +
                "id, user_id, password, name, email, created_at, updated_at " +
                "from ns_user where id = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getTimestamp(6).toLocalDateTime(),
                toLocalDateTime(rs.getTimestamp(7))
        );

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, applicantId));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
