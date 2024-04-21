package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.image.CoverImage;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static nextstep.courses.domain.session.FreeSession.FREE_FEE;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;
    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session " +
                "(session_id, fee, max_enrollment," +
                "number_of_students, start_date, end_date, " +
                "image_type, image_file_size, image_width, " +
                "image_height, session_status, is_open_for_enrollment) " +
                "values(?,?,?," +
                "?,?,?," +
                "?,?,?," +
                "?,?,?)";

        return jdbcTemplate.update(
                sql,
                session.getSessionId(),
                session.getFee(),
                session.getMaxEnrollments(),
                session.getNumberOfStudents(),
                session.getSessionPeriod().getStartDate(),
                session.getSessionPeriod().getEndDate(),
                null,
                null,
                null,
                null,
                session.getSessionStatus().toString(),
                session.isOpenForEnrollment()
        );
    }

    @Override
    public Session findBySessionId(Long sessionId) {
        String sql = "select fee, max_enrollment, number_of_students, " +
                "start_date, end_date, image_type, " +
                "image_file_size, image_width, image_height, " +
                "session_status, is_open_for_enrollment " +
                "from session where session_id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            Long fee = rs.getLong("fee");
            SessionPeriod sessionPeriod = SessionPeriod.of(toLocalDateTime(rs.getTimestamp("start_date")), toLocalDateTime(rs.getTimestamp("end_date")));
            SessionStatusEnum sessionStatus = SessionStatusEnum.valueOf(rs.getString("session_status"));

            if (fee.equals(FREE_FEE)) {
                return new FreeSession(
                        sessionId,
                        sessionPeriod,
                        sessionStatus,
                        rs.getInt("number_of_students"),
                        rs.getBoolean("is_open_for_enrollment")
                );
            }

            return new PaidSession(
                    sessionId,
                    sessionPeriod,
                    sessionStatus,
                    rs.getBoolean("is_open_for_enrollment"),
                    rs.getInt("number_of_students"),
                    rs.getInt("max_enrollment"),
                    fee
            );
        };

        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
