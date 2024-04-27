package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.Student;
import nextstep.sessions.domain.StudentRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcStudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(NsUser student, Session session) {
        String sql = "insert into students(ns_user_id, session_id) values(?, ?)";
        return jdbcTemplate.update(sql, student.getId(), session.getId());
    }

    @Override
    public Student findByNsUserId(long nsUserId) {
        String sql = "select * from students where ns_user_id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Student(rs.getLong("ns_user_id"), rs.getLong("session_id"))
                , nsUserId);
    }
}
