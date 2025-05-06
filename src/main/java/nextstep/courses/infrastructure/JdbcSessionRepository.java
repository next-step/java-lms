package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session(id, cover_image_id, start_at, end_at, status, price, max_capacity, enrolled_count) values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            session.getId(),
            session.getCoverImage() != null ? session.getCoverImage().getId() : null,
            session.startAt(),
            session.endAt(),
            session.getStatus().toString(),
            session.price(),
            session.getMaxCapacity(),
            0
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select * from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rownum) -> {
            return null;
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
