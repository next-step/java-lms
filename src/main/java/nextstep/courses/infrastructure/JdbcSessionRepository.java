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

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Session> findBy(long sessionId, SessionUsers sessionUsers) {
        String sql = "select id, course_id, start_date, end_date, price, " +
                "state, type, user_count, max_user_count, image_size, image_extension, image_width, " +
                "image_height " +
                "from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getLong(2),
                new SessionImage(
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getInt(13)
                ),
                new SessionPeriod(
                        toLocalDate(rs.getDate(3)),
                        toLocalDate(rs.getDate(4))
                ),
                new SessionPrice(
                        rs.getInt(5)
                ),
                SessionState.valueOf(rs.getString(6)),
                SessionType.valueOf(rs.getString(7)),
                sessionUsers,
                new SessionUserCount(
                        rs.getInt(8),
                        rs.getInt(9)
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

    @Override
    public void updateCountBy(int userCount, long sessionId) {
        String sql = "update session set user_count=? where id=?";
        this.jdbcTemplate.update(sql, userCount, sessionId);
    }

}
