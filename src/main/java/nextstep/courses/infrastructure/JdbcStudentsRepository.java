package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.students.Students;
import nextstep.courses.domain.students.StudentsRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class JdbcStudentsRepository implements StudentsRepository {
    private JdbcOperations jdbcTemplate;

    @Override
    public Students findBySession(Session session) {
        String sql = "select user_id, session_id from session where session_id = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(rs.getLong(1));

        List<NsUser> nsUsers = jdbcTemplate.query(sql, rowMapper, session.getId());

        return new Students(nsUsers);
    }

    @Override
    public void save(Session session, NsUser nsUser) {
        String sql = "insert into students (user_id, session_id) values (?, ?)";
        jdbcTemplate.update(sql, nsUser.getId(), session.getId());
    }
}
