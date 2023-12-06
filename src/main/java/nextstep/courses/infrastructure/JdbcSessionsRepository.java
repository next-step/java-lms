package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.domain.session.SessionsRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("sessionsRepository")
public class JdbcSessionsRepository implements SessionsRepository {
    private final JdbcOperations jdbcTemplate;

    private final SessionRepository sessionRepository;

    public JdbcSessionsRepository(JdbcOperations jdbcTemplate, SessionRepository sessionRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionRepository = sessionRepository;
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
    public Sessions findByCourseId(Long sessionId) {
        String sql = "select session_id from course_session where course_id = ?";
        List<Long> sessionIds = jdbcTemplate.queryForList(sql, Long.class, sessionId);
        return sessionIds.stream()
                .map(session -> sessionRepository.findById(session))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Sessions::of));
    }
}
