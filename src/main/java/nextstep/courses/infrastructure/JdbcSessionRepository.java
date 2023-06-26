package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JdbcSessionRepository {
    private final JdbcOperations jdbcTemplate;
    private final JdbcStudentRepository jdbcStudentRepository;

    @Transactional(readOnly = true)
    public Session findById(Long id) {
        List<Student> students = jdbcStudentRepository.findBySessionId(id);

        String sql = "select id, start_date, end_date, " +
                "cover_url, bill_type, price, " +
                "max_enrollment, progress_status, course_id " +
                "from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getDate(2).toLocalDate(),
                rs.getDate(3).toLocalDate(),
                rs.getString(4),
                Session.BillType.valueOf(rs.getString(5)),
                new Price(rs.getInt(6)),
                rs.getLong(7),
                SessionEnrollmentContext.Status.valueOf(rs.getString(8)),
                rs.getLong(9),
                students
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Transactional(readOnly = true)
    public List<Session> findByCourseId(Long courseId) {
        String sql = "select id, start_date, end_date, " +
                "coverUrl, billType, price, " +
                "maxEnrollment, progressStatus, courseId " +
                "from session where courseId = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getDate(2).toLocalDate(),
                rs.getDate(3).toLocalDate(),
                rs.getString(4),
                Session.BillType.valueOf(rs.getString(5)),
                new Price(rs.getInt(6)),
                rs.getLong(7),
                SessionEnrollmentContext.Status.valueOf(rs.getString(8)),
                rs.getLong(9),
                jdbcStudentRepository.findBySessionId(rs.getLong(1))
        );
        return jdbcTemplate.query(sql, rowMapper, courseId);
    }

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, JdbcStudentRepository jdbcStudentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcStudentRepository = jdbcStudentRepository;
    }

    public int save(Session session) {
        String sql = "insert into session (start_date, end_date, cover_url, bill_type, price, max_enrollment, progress_status, course_id) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                session.getDateTray().getStartDate(),
                session.getDateTray().getEndDate(),
                session.getCoverUrl(),
                session.getBillType().name(),
                session.getPrice().getValue(),
                session.getEnrollmentContext().getMaxEnrollment(),
                session.getEnrollmentContext().getProgressStatus().name(),
                session.getCourseId()
        );
    }
}
