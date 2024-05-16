package nextstep.session.infrastructure;

import java.util.List;
import nextstep.session.domain.EnrollmentPolicy;
import nextstep.session.domain.EnrollmentStatus;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionCoverImage;
import nextstep.session.domain.SessionEnrollment;
import nextstep.session.domain.SessionProgressStatus;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SessionSchedule;
import nextstep.session.domain.Student;
import nextstep.session.domain.Students;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id,title, course_id,start_date,end_date,progress_status,enrollment_status, price_type,max_enrollment, fee from session where id = ?";
        String image_sql = "select id, session_id,width, height,size, image_type from session_image where session_id = ?";
        String student_sql = "select id, user_id, session_id, enrollment_approval_status from session_student where session_id = ?";

        RowMapper<SessionCoverImage> imageRowMapper = (rs, rowNum) ->
            new SessionCoverImage(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getLong(4),
                rs.getLong(5),
                rs.getString(6)
            );
        List<SessionCoverImage> images = jdbcTemplate.query(image_sql, imageRowMapper, id);
        RowMapper<Student> studentRowMapper = (rs, rowNum) ->
            new Student(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getString(4)
            );
        Students students = new Students(jdbcTemplate.query(student_sql, studentRowMapper, id));
        RowMapper<Session> rowMapper = (rs, rowNum) ->
            new Session(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                new SessionSchedule(rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate()),
                images,
                SessionProgressStatus.convert(rs.getString(6)),
                new SessionEnrollment(EnrollmentStatus.convert(rs.getString(7)),
                    new EnrollmentPolicy(
                        rs.getString(8), rs.getInt(9), rs.getInt(10)), students));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session(title,course_id, start_date, end_date,progress_status, enrollment_status,price_type,  max_enrollment,fee ) values(?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, session.getTitle(), session.getCourseId(),
            session.getStartDate(), session.getEndDate(), session.getSessionProgressStatus(),
            session.getSessionEnrollmentStatus(),
            session.getPriceType(), session.getMaxEnrollment(), session.getFee());
    }
}
