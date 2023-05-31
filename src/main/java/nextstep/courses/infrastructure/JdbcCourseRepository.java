package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsTeacher;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Course course) {
        String sql = "insert into course (title, creator_id, cohort, created_at) values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, course.getTitle(), course.getCreatorId(), course.getCohort(), course.getCreatedAt());
    }

    @Override
    public Course findById(Long id) {
        String sql = "select id, title, creator_id, cohort, created_at, updated_at from course where id = ?";
        RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                rs.getInt(4),
                toLocalDateTime(rs.getTimestamp(5)),
                toLocalDateTime(rs.getTimestamp(6)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }


    public Course findByIdJoinSession(Long id) {
        String sql = "select c.id, c.title, c.creator_id, c.cohort, c.created_at, c.updated_at, " +
                "s.name, s.start_date, s.end_date, s.cover_image, s.payment_type, s.status, s.enrollment, s.registered_student, s.max_student " +
                "from course as c join session as s on c.id = s.course_id " +
                "where c.id = ?";

        RowMapper<Course> rowMapper = (rs, rowNum) -> {
            Course course = new Course(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getLong(3),
                    rs.getInt(4),
                    toLocalDateTime(rs.getTimestamp(5)),
                    toLocalDateTime(rs.getTimestamp(6)));

            List<Session> sessionList = new ArrayList<>();
            do {
                Session session = new Session(
                        rs.getString(7),
                        new SessionPeriod(toLocalDate(rs.getDate(8)), toLocalDate(rs.getDate(9))),
                        rs.getString(10),
                        new SessionOption(SessionType.find(rs.getString(11)),
                                SessionStatus.find(rs.getString(12)),
                                SessionEnrollment.find(rs.getString(13))),
                        new SessionCapacity(rs.getInt(14),
                                rs.getInt(15)),
                        new NsTeacher(),
                        rs.getLong(1)
                );
                sessionList.add(session);
            } while (rs.next());

            course.setSessions(sessionList);
            return course;
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
