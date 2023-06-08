package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.enrollment.Capacity;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.Student;
import nextstep.courses.domain.enrollment.Students;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import static nextstep.courses.util.RepositoryUtils.toLocalDateTime;

@Repository
public class JdbcSessionRepository implements JdbcRepository<Session> {
    private final JdbcOperations jdbcTemplate;
    private final JdbcStudentRepository studentRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, JdbcStudentRepository studentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.studentRepository = studentRepository;
    }

    @Override
    public Session findById(Long id) {
        String sql = "select session.id, course_id, title, url, start_date, end_date, type, status, capacity, created_at, updated_at " +
                "from session " +
                "inner join image on session.image_id = image.id " +
                "where session.id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            List<Student> students = studentRepository.findAllBySessionId(id);

            Enrollment enrollment = new Enrollment(
                    SessionStatus.valueOf(rs.getString(8)),
                    SessionType.valueOf(rs.getString(7)),
                    new Students(Set.copyOf(students), new Capacity(rs.getInt(9)))
            );

            return new Session(
                    rs.getLong(1),
                    rs.getLong(2),
                    rs.getString(3),
                    new CoverImage(rs.getString(4)),
                    new SessionPeriod(
                            rs.getDate(5).toLocalDate(),
                            rs.getDate(6).toLocalDate()
                    ),
                    enrollment,
                    toLocalDateTime(rs.getTimestamp(10)),
                    toLocalDateTime(rs.getTimestamp(11))
            );
        };

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session " +
                "(course_id, title, image_id, start_date, end_date, type, status, capacity, created_at) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                session.getCourseId(),
                session.getTitle(),
                session.getCoverImage().getId(),
                Date.valueOf(session.getPeriod().getStartDate()),
                Date.valueOf(session.getPeriod().getEndDate()),
                session.getEnrollment().getType().toString(),
                session.getEnrollment().getStatus().toString(),
                session.getEnrollment().getCapacity(),
                session.getCreatedAt()
        );
    }
}
