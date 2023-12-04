package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.repository.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository("sessionRepository")
public class JdbcFreeSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    private JdbcFreeSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(long courseId, long imageId, Session session) {
        LocalDate startDate = session.getProgressPeriod().getStartDate();
        LocalDate endDate = session.getProgressPeriod().getEndDate();
        String sql = "insert into session (course_id, image_id, start_date, end_date, state, type, fee, max_apply) value(?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, courseId, imageId, startDate, endDate, session.getState(), "FREE", 0, 0);
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, start_date, end_date, state from session where id = ?";
        return null;
    }
}
