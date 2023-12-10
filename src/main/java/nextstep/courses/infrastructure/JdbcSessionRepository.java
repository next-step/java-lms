package nextstep.courses.infrastructure;

import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Session;
import nextstep.courses.enumeration.SessionProgressType;
import nextstep.courses.enumeration.SessionRecruitStatus;
import nextstep.courses.enumeration.SessionType;
import nextstep.courses.repository.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session " +
                     "(id, course_id, title, session_type, max_participants, price, session_recruit_status, session_progress_type, start_at, end_at, created_at, updated_at) " +
                     "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                session.getId(),
                session.getCourseId(),
                session.getTitle(),
                session.getSessionType().name(),
                session.getMaxParticipants(),
                session.getPrice(),
                session.getSessionRecuitStatus().name(),
                session.getSessionProgressType().name(),
                session.getStartAt(),
                session.getEndAt(),
                session.getCreatedAt(),
                session.getUpdatedAt());
    }

    @Override
    public int updateTitle(String title, Long id) {
        String sql = "update session set title = ? where id = ?";
        return jdbcTemplate.update(sql,
                title,
                id);
    }

    @Override
    public int delete(Long id) {
        String sql = "delete from session where id = ?";
        return jdbcTemplate.update(sql,
                id);
    }

    @Override
    public Optional<Session> findById(Long id) {
        String sql = "select id, course_id, title, session_type, max_participants, price,"
                + "session_recruit_status, session_progress_type, start_at, end_at, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> FreeSession.of(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                SessionType.valueOf(rs.getString(4)),
                rs.getInt(5),
                rs.getInt(6),
                SessionRecruitStatus.valueOf(rs.getString(7)),
                SessionProgressType.valueOf(rs.getString(8)),
                rs.getTimestamp(9).toLocalDateTime(),
                rs.getTimestamp(10).toLocalDateTime(),
                rs.getTimestamp(11).toLocalDateTime(),
                rs.getTimestamp(12).toLocalDateTime());

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }
}
