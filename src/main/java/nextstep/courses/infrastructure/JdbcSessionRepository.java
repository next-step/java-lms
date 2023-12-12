package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionInfo;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.enrollment.Enrollment;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    private final GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Session session) {
        String sql = "insert into session (type, progress_state, recruit_state, start_date, end_date, amount, enrollment_max) values(?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, session.type());
            preparedStatement.setObject(2, session.progressStateValue());
            preparedStatement.setObject(3, session.recruitStateValue());
            preparedStatement.setObject(4, session.startDate());
            preparedStatement.setObject(5, session.endDate());
            preparedStatement.setObject(6, session.amount());
            preparedStatement.setObject(7, session.enrollmentMax());

            return preparedStatement;
        }, generatedKeyHolder);

        return generatedKeyHolder.getKey().longValue();
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, type, progress_state, recruit_state, start_date, end_date, amount, enrollment_max from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> Session.of(
                rs.getLong(1),
                SessionInfo.of(rs.getString(2), rs.getDate(5).toLocalDate(), rs.getDate(6).toLocalDate()),
                Enrollment.of(rs.getString(3), rs.getString(4), rs.getLong(7), rs.getLong(8), rs.getString(2)),
                null,
                null);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
