package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.repository.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (image_id, session_status, price, capacity, start_time, end_time) values (?, ?, ?, ?, ?, ?)";


        return jdbcTemplate.update(sql,
                session.getImageId(),
                session.getSessionStatus(),
                session.getPrice(),
                session.getCapacity(),
                session.getStartTime(),
                session.getEndTime());
    }

    @Override
    public Session findById(long id) {
        String sql = "select * from session where id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {

            // 1️⃣ ImageFile (지금은 ID만 복원)
            ImageFile imageFile = new ImageFile(rs.getLong("image_id"));

            // 2️⃣ SessionPeriod
            SessionPeriod period = new SessionPeriod(
                    rs.getObject("start_time", LocalDateTime.class),
                    rs.getObject("end_time", LocalDateTime.class)
            );

            // 3️⃣ SessionStatus
            SessionStatus status =
                    SessionStatus.valueOf(rs.getString("session_status"));

            // 4️⃣ EnrollmentRule
            Integer price = rs.getObject("price", Integer.class);

            EnrollmentRule enrollmentRule;
            if (price != null) {
                enrollmentRule = new PaidEnrollmentRule(
                        price,
                        rs.getInt("capacity")
                );
            } else {
                enrollmentRule = new FreeEnrollmentRule();
            }

            // 5️⃣ Enrollments (조회 시점에서는 비어 있음)
            Enrollments enrollments = new Enrollments();

            return new Session(
                    rs.getLong("id"),
                    imageFile,
                    period,
                    status,
                    enrollmentRule,
                    enrollments
            );
        }, id);
    }
}
