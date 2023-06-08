package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;

import static nextstep.courses.util.RepositoryUtils.toLocalDateTime;

@Repository
public class JdbcSessionRepository implements JdbcRepository<Session> {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session findById(Long id) {
        String sql = "select session.id, course_id, title, url, start_date, end_date, type, status, capacity, created_at, updated_at " +
                "from session " +
                "inner join image on session.image_id = image.id " +
                "where session.id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                new CoverImage(rs.getString(4)),
                new SessionPeriod(
                        rs.getDate(5).toLocalDate(),
                        rs.getDate(6).toLocalDate()
                ),
                SessionType.valueOf(rs.getString(7)),
                SessionStatus.valueOf(rs.getString(8)),
                rs.getInt(9),
                toLocalDateTime(rs.getTimestamp(10)),
                toLocalDateTime(rs.getTimestamp(11))
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session " +
                "(course_id, title, image_id, start_date, end_date, type, status, capacity, created_at) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                session.getCourseId(),
                session.getTitle(),
                session.getCoverImage().getId(),
                Date.valueOf(session.getPeriod().getStartDate()),
                Date.valueOf(session.getPeriod().getEndDate()),
                session.getType().toString(),
                session.getStatus().toString(),
                session.getCapacity(),
                session.getCreatedAt()
        );
    }
}
