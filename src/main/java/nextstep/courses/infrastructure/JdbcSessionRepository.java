package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (id, maximum_number_of_student, started_at, ended_at, status, amount, type, " +
                "image_size_of_bytes, image_width, image_height, image_type) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                session.getId(),
                session.getMaximumNumberOfStudent(),
                session.getStartedAt(),
                session.getEndedAt(),
                session.getStatus().toString(),
                session.getAmount(),
                session.getType().toString(),
                session.getCoverImage().getSizeOfBytes(),
                session.getCoverImage().getWidth(),
                session.getCoverImage().getHeight(),
                session.getCoverImage().getType().toString()
        );
    }

    @Override
    public Session findById(Long id) {
        try {
            String sql = "select id, maximum_number_of_student, started_at, ended_at, status, amount, type, " +
                    "image_size_of_bytes, image_width, image_height, image_type " +
                    "from session where id = ?";

            RowMapper<Session> rowMapper = (rs, rowNum) -> {
                String type = rs.getString(6);
                SessionCoverImage coverImage = new SessionCoverImage(rs.getInt(8), Enum.valueOf(ImageType.class, rs.getString(11)), rs.getInt(9), rs.getInt(10));

                if (type.equals("FREE")) {
                    return new FreeSession(
                            rs.getLong(1),
                            toLocalDateTime(rs.getTimestamp(3)),
                            toLocalDateTime(rs.getTimestamp(4)),
                            List.of(coverImage),
                            Enum.valueOf(SessionType.class, rs.getString(7))
                    );
                }

                return new PaidSession(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(6),
                        toLocalDateTime(rs.getTimestamp(3)),
                        toLocalDateTime(rs.getTimestamp(4)),
                        List.of(coverImage),
                        Enum.valueOf(SessionType.class, rs.getString(7))
                );
            };
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
