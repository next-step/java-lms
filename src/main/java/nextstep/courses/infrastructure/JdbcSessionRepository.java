package nextstep.courses.infrastructure;

import java.time.LocalDateTime;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;

public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session "
                + "(start_date, end_date, cover_image_file_name, cover_image_size, cover_image_type, "
                + "cover_image_width, cover_image_height, policy_type, price, capacity, session_status, created_at) "
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql, session.period().startDate(), session.period().endDate(),
                session.coverImageName(), session.coverImageSize(), session.coverImageType(),
                session.coverImageWidth(), session.coverImageHeight(),
                session.type().name(), session.price(), session.capacity(), session.status().name(), LocalDateTime.now()
        );
    }

    @Override
    public Session findById(Long id) {
        return null;
    }
}
