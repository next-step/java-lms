package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.model.SessionImage;
import nextstep.courses.domain.model.SessionPeriod;
import nextstep.courses.domain.model.SessionStatus;
import nextstep.courses.domain.repository.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (course_id, capacity, status, price, start_date, end_date, image_path, image_file, creator_id, created_at, updated_at) "
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                session.getCourseId(),
                session.getStudents().getCapacity(),
                session.getStatus().name(),
                new BigDecimal(session.getPrice()),
                session.getPeriod().getStartDate().toLocalDate(),
                session.getPeriod().getEndDate().toLocalDate(),
                session.getImage().getPath(),
                session.getImage().getFile(),
                session.getCreatorId(),
                session.getCreatedAt(),
                session.getUpdatedAt());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, capacity, status, price, start_date, end_date, image_path, image_file, creator_id, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            try {
                return new Session(
                        rs.getLong(1),
                        rs.getLong(2),
                        new SessionPeriod(
                                rs.getDate(6).toLocalDate().atStartOfDay(),
                                rs.getDate(7).toLocalDate().atStartOfDay()
                        ),
                        new SessionImage(
                                rs.getString(8),
                                rs.getBlob(9) == null ? null : rs.getBlob(9).getBinaryStream().readAllBytes()
                        ),
                        SessionStatus.valueOf(rs.getString(4)),
                        rs.getLong(5),
                        rs.getInt(3),
                        rs.getLong(10),
                        toLocalDateTime(rs.getTimestamp(11)),
                        toLocalDateTime(rs.getTimestamp(12)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
