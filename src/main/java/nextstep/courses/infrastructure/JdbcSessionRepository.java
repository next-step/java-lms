package nextstep.courses.infrastructure;

import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (maximum_number_of_student, started_at, ended_at, status, amount, type, " +
                "image_size_of_bytes, image_width, image_height, image_type) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                session.getMaximumNumberOfStudent(),
                session.getStartedAt(),
                session.getEndedAt(),
                session.getStatus(),
                session.getAmount(),
                session.getType(),
                session.getCoverImage().getSizeOfBytes(),
                session.getCoverImage().getWidth(),
                session.getCoverImage().getHeight(),
                session.getCoverImage().getType()
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, maximum_number_of_student, started_at, ended_at, status, amount, type, " +
                "image_size_of_bytes, image_width, image_height, image_type " +
                "from session where id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            String type = rs.getString(6);
            if (type.equals("FREE")) {
                return new FreeSession(
                        rs.getLong(1),
                        toLocalDateTime(rs.getTimestamp(3)),
                        toLocalDateTime(rs.getTimestamp(4))
                );
            }

            return new PaidSession(
                    rs.getLong(1),
                    rs.getLong(2),
                    0,
                    toLocalDateTime(rs.getTimestamp(4)),
                    toLocalDateTime(rs.getTimestamp(5)));
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
