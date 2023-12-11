package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionInfo;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.domain.session.SessionsRepository;
import nextstep.courses.domain.session.enrollment2.EnrollmentInfo;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("sessionsRepository")
public class JdbcSessionsRepository implements SessionsRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionsRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(long courseId, Sessions sessions) {
        String sql = "insert into course_session (course_id, session_id) values(?, ?)";

        return sessions.values()
                .stream()
                .mapToInt(session -> jdbcTemplate.update(sql, courseId, session.id()))
                .sum();
    }

    @Override
    public Sessions findByCourseId(Long courseId) {
        String sql = "select a.id, a.type, a.progress_state, a.recruit_state, a.start_date, a.end_date, a.amount, a.enrollment_max from session a inner join course_session b on a.id = b.session_id where b.course_id = ?";
        return Sessions.of(
                jdbcTemplate.query(
                        sql,
                        (rs, rowNum) -> Session.of(
                                rs.getLong(1),
                                SessionInfo.of(rs.getString(2), rs.getDate(5).toLocalDate(), rs.getDate(6).toLocalDate()),
                                EnrollmentInfo.of(rs.getString(3), rs.getString(4), rs.getLong(7), rs.getLong(8), rs.getString(2)),
                                null,
                                null),
                        courseId
                )
        );
    }
}
