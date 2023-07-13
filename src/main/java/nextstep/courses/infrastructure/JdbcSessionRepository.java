package nextstep.courses.infrastructure;

import nextstep.courses.code.SessionStatus;
import nextstep.courses.code.SessionType;
import nextstep.courses.domain.*;
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
        String sql = "insert into session(course_id, generation, cover_image, `type`, status, head_count, start_at, end_at) values(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(
                sql,
                session.getCourse().getId(),
                session.getEssentialInfo().getGeneration(),
                session.getEssentialInfo().getCoverImage(),
                session.getType().name(),
                session.getStatus().name(),
                session.getEssentialInfo().getHeadCount(),
                session.getPeriod().getStartAt(),
                session.getPeriod().getEndAt()
        );
    }

    public Session findById(Long id) {
        String sql = "select * from session where id =?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong("id"),
                new Course(rs.getLong("course_id")),
                new SessionEssentialInfo(
                        rs.getInt("generation"),
                        rs.getString("cover_image"),
                        rs.getInt("head_count")
                ),
                SessionType.valueOf(rs.getString("type")),
                SessionStatus.valueOf(rs.getString("status")),
                new SessionPeriod(
                        rs.getTimestamp("start_at").toLocalDateTime(),
                        rs.getTimestamp("end_at").toLocalDateTime()
                )
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

}
