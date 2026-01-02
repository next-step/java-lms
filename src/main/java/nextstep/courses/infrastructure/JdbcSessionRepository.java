package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.repository.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;


@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session save(Session session) {
        String sql = "insert into session (session_type, recruiting_status, progress_status, price, capacity, start_time, end_time) values (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connect -> {
            PreparedStatement ps = connect.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, session.getType());
            ps.setString(2 , session.getRecruitingStatus());
            ps.setString(3 , session.getProgressStatus());
            extractedPrice(session, ps);
            extractedCapacity(session, ps);
            ps.setTimestamp(6, Timestamp.valueOf(session.getStartTime()));
            ps.setTimestamp(7, Timestamp.valueOf(session.getEndTime()));
            return ps;
        }, keyHolder);


        long id = keyHolder.getKey().longValue();

        return new Session(id, session.getStartTime(), session.getEndTime(), session.getRecruitingStatus(), session.getProgressStatus(), session.getType(), session.getPrice(), session.getCapacity());
    }

    private static void extractedCapacity(Session session, PreparedStatement ps) throws SQLException {
        if (session.getCapacity() != null) {
            ps.setInt(5, session.getCapacity());
        } else {
            ps.setNull(5, Types.INTEGER);
        }
    }

    private static void extractedPrice(Session session, PreparedStatement ps) throws SQLException {
        if (session.getPrice() != null) {
            ps.setInt(4, session.getPrice());
        } else {
            ps.setNull(4, Types.INTEGER);
        }
    }

    public Session findById(long id) {
        String sql = "select " +
                "s.id, " +
                "s.recruiting_status, " +
                "s.progress_status, " +
                "s.session_type, " +
                "s.price, " +
                "s.capacity, " +
                "s.start_time, " +
                "s.end_time, " +
                "from session s " +
                "where s.id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {

            Session session = new Session(
                    rs.getLong("id"),
                    rs.getObject("start_time", LocalDateTime.class),
                    rs.getObject("end_time", LocalDateTime.class),
                    rs.getString("recruiting_status"),
                    rs.getString("progress_status"),
                    rs.getString("session_type"),
                    rs.getObject("price", Integer.class),
                    rs.getObject("capacity", Integer.class)
            );

            return session;
        }, id);
    }


}
