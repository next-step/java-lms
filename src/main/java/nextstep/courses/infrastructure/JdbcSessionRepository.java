package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.courses.enums.SessionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

import static nextstep.courses.utils.DateUtil.toLocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (begin_dt, end_dt, session_status, capacity, price, course_id, session_cover_id, created_at, updated_at) values(?, ?, ?, ?, ?, ?, ?, ? ,?)";
        return jdbcTemplate.update(sql, session.period().getBeginDt(), session.period().getBeginDt(), session.status(), session.capacity().capacity(),
                session.price().price(), session.course().id(), session.sessionCover().id(), LocalDateTime.now(), LocalDateTime.now());
    }

    @Override
    public Optional<Session> findById(Long id) {
        String sql = "select id, begin_dt, end_dt, session_status, capacity, price, " +
                "course_id, session_cover_id, created_at, updated_at " +
                "from session where id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                toLocalDateTime(rs.getTimestamp(2)),
                toLocalDateTime(rs.getTimestamp(3)),
                SessionStatus.valueOf(rs.getString(4)),
                new Capacity(rs.getInt(5)),
                new Price(rs.getInt(6)),
                new Course(rs.getLong(7)),
                new SessionCover(rs.getLong(8)),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10))
        );

        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }
}
