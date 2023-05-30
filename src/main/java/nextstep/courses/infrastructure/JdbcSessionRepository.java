package nextstep.courses.infrastructure;

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
    public int save(Session session, Long courseId) {
        String sql = "insert into session " +
                "(course_id, title, start_date, end_date, charge_type, image_url" +
                ", status_type, max_student_number, creator_id, created_at) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql
                , courseId
                , session.getTitle()
                , session.getSessionPeriod().getStartDate()
                , session.getSessionPeriod().getEndDate()
                , session.getChargeType().toString()
                , session.getCoverImageUrl()
                , session.getStatusType().toString()
                , session.getSessionStudents().getMaximumNumber()
                , session.getCreatorId()
                , session.getCreatedAt()
        );
    }

    @Override
    public Session findById(int id) {
        String sql = "select id, title, start_date, end_date, charge_type, image_url" +
                ", status_type, max_student_number, creator_id, created_at, updated_at" +
                " from session" +
                " where id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getInt(1),
                rs.getString(2),
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(3))
                        , toLocalDateTime(rs.getTimestamp(4))),
                ChargeType.find(rs.getString(5)),
                new ImageUrl(rs.getString(6)),
                SessionStatusType.find(rs.getString(7)),
                new SessionStudents(rs.getInt(8)),
                rs.getLong(9),
                toLocalDateTime(rs.getTimestamp(10)),
                toLocalDateTime(rs.getTimestamp(11))
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
