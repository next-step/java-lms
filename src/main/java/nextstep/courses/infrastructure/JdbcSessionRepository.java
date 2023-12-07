package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private SessionUsersRepository sessionUsersRepository;

    private SessionImageRepository sessionImageRepository;

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(SessionImageRepository sessionImageRepository, SessionUsersRepository sessionUsersRepository, JdbcOperations jdbcTemplate) {
        this.sessionImageRepository = sessionImageRepository;
        this.sessionUsersRepository = sessionUsersRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Session> findBy(long sessionId) {
        final SessionUsers sessionUsers = sessionUsersRepository.findBy(sessionId);
        final SessionImages sessionImages = sessionImageRepository.findBy(sessionId);

        String sql = "select id, course_id, start_date, end_date, price, " +
                "type, status, recruitment, max_user_count " +
                "from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getLong(2),
                sessionImages,
                new SessionPeriod(
                        toLocalDate(rs.getDate(3)),
                        toLocalDate(rs.getDate(4))
                ),
                new Enrollment(
                        new SessionPrice(
                                rs.getInt(5)
                        ),
                        SessionStatus.valueOf(rs.getString(7)),
                        SessionRecruitment.valueOf(rs.getString(8)),
                        SessionType.valueOf(rs.getString(6)),
                        new SessionUserCount(
                                sessionUsers.size(),
                                rs.getInt(9)
                        ),
                        sessionUsers
                )
        );
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, sessionId));
    }

    private LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }

}
