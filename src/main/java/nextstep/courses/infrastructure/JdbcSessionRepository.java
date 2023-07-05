package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (course_id, image, start_date, end_date, lecture_status, lecture_type, max_user, created_at) values(?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                session.getCourseId(),
                session.getImage(),
                session.getStartDate(),
                session.getEndDate(),
                session.getLectureStatus().name(),
                session.getLectureType().name(),
                session.getMaxUser(),
                session.getCreatedAt()
        );
    }

    @Override
    public Session findById(long id) {
        String sql = "select id,course_id,image,lecture_type,start_date,end_date,lecture_status,max_user,created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                LectureType.valueOf(rs.getString(4)),
                new SessionDate(
                        LocalDate.parse(rs.getString(5)),
                        LocalDate.parse(rs.getString(6))
                ),
                new SessionInfo(
                        LectureStatus.valueOf(rs.getString(7)),
                        new Students(),
                        rs.getInt(8)
                ),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
