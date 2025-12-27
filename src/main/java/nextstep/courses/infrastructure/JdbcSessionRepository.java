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
    public Long save(Session session) {
        String sql = "insert into session (image_id, session_status, price, capacity, start_time, end_time) values (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connect -> {
            PreparedStatement ps = connect.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, session.getImageId());
            ps.setString(2 , session.getSessionStatus());
            ps.setInt(3 , session.getPrice());
            ps.setInt(4 , session.getCapacity());
            extractedPrice(session, ps);
            extractedCapacity(session, ps);
            ps.setTimestamp(5, Timestamp.valueOf(session.getStartTime()));
            ps.setTimestamp(6, Timestamp.valueOf(session.getEndTime()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private static void extractedCapacity(Session session, PreparedStatement ps) throws SQLException {
        if (session.getCapacity() != null) {
            ps.setInt(4, session.getCapacity());
        } else {
            ps.setNull(4, Types.INTEGER);
        }
    }

    private static void extractedPrice(Session session, PreparedStatement ps) throws SQLException {
        if (session.getPrice() != null) {
            ps.setInt(3, session.getPrice());
        } else {
            ps.setNull(3, Types.INTEGER);
        }
    }

    public Session findById(long id) {
        String sql = "select " +
                "s.id, " +
                "s.session_status, " +
                "s.price, " +
                "s.capacity, " +
                "s.start_time, " +
                "s.end_time, " +
                "i.id AS image_id, " +
                "i.size, " +
                "i.image_type, " +
                "i.width, " +
                "i.height " +
                "from session s " +
                "join image_file i " +
                "on s.image_id = i.id " +
                "where s.id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {

            ImageFile imageFile = new ImageFile(
                    rs.getLong("image_id"),
                    rs.getLong("size"),
                    rs.getString("image_type"),
                    rs.getInt("width"),
                    rs.getInt("height")
            );

            Session session = new Session(
                    rs.getLong("id"),
                    imageFile,
                    rs.getObject("start_time", LocalDateTime.class),
                    rs.getObject("end_time", LocalDateTime.class),
                    rs.getString("session_status"),
                    rs.getObject("price", Integer.class),
                    rs.getObject("capacity", Integer.class)
            );

            return session;
        }, id);
    }


}
