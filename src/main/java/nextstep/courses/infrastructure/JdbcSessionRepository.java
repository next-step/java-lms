package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.*;
import nextstep.courses.repository.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcOperations;

    public JdbcSessionRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public int create(Session newSession) {
        String sql = "insert into course_session (title, session_number, course_id, start_date, end_date, image_url, fee_type, status, capacity, creator_id, created_at) values (?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcOperations.update(sql,
                newSession.getTitle(),
                newSession.getSessionNumber(),
                newSession.getCourseId(),
                newSession.getSessionPeriod().getStartDate(),
                newSession.getSessionPeriod().getEndDate(),
                newSession.getCoverImage().getUrl(),
                newSession.getStudents().getSessionFeeType().name(),
                newSession.getStudents().getSessionStatus().name(),
                newSession.getStudents().getCapacity(),
                newSession.getCreatorId(),
                newSession.getCreatedAt());
    }

    @Override
    public List<Session> findByCourseId(Long courseId) {
        String sql = "select \n" +
                "                    id,\n" +
                "                    title,\n" +
                "                    course_id,\n" +
                "                    session_number,\n" +
                "                    start_date,\n" +
                "                    end_date,\n" +
                "                    image_url,\n" +
                "                    fee_type,\n" +
                "                    status,\n" +
                "                    capacity,\n" +
                "                    creator_id,\n" +
                "                    created_at,\n" +
                "                    updated_at from course_session where course_id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                rs.getLong(4),
                new SessionPeriod(toLocalDate(rs.getTimestamp(5)), toLocalDate(rs.getTimestamp(6))),
                new CoverImage(rs.getString(7)),
                new Students(
                        rs.getInt(10),
                        SessionFeeType.valueOf(rs.getString(8)),
                        SessionStatus.valueOf(rs.getString(9))),
                rs.getLong(11),
                toLocalDateTime(rs.getTimestamp(12)),
                toLocalDateTime(rs.getTimestamp(13)));
        return jdbcOperations.query(sql, rowMapper, courseId);
    }

    @Override
    public Session findById(Long sessionId) {
        String sql = "select \n" +
                "                    id,\n" +
                "                    title,\n" +
                "                    course_id,\n" +
                "                    session_number,\n" +
                "                    start_date,\n" +
                "                    end_date,\n" +
                "                    image_url,\n" +
                "                    fee_type,\n" +
                "                    status,\n" +
                "                    capacity,\n" +
                "                    creator_id,\n" +
                "                    created_at,\n" +
                "                    updated_at from course_session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                rs.getLong(4),
                new SessionPeriod(toLocalDate(rs.getTimestamp(5)), toLocalDate(rs.getTimestamp(6))),
                new CoverImage(rs.getString(7)),
                new Students(
                        rs.getInt(10),
                        SessionFeeType.valueOf(rs.getString(8)),
                        SessionStatus.valueOf(rs.getString(9))),
                rs.getLong(11),
                toLocalDateTime(rs.getTimestamp(12)),
                toLocalDateTime(rs.getTimestamp(13)));
        return jdbcOperations.queryForObject(sql, rowMapper, sessionId);
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
