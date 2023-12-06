package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Students;
import nextstep.courses.domain.session.StudentsRepository;
import nextstep.users.domain.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("studentsRepository")
public class JdbcStudentsRepository implements StudentsRepository {
    private JdbcOperations jdbcTemplate;

    private final UserRepository userRepository;

    public JdbcStudentsRepository(JdbcOperations jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }


    @Override
    public int save(long sessionId, Students students) {
        String sql = "insert into session_student (session_id, user_id) values(?, ?)";

        return students.values()
                .stream()
                .mapToInt(student -> jdbcTemplate.update(sql, sessionId, student.getUserId()))
                .sum();
    }

    @Override
    public Students findBySessionId(Long sessionId) {
        String sql = "select user_id from session_student where session_id = ?";
        List<String> userIds = jdbcTemplate.queryForList(sql, String.class, sessionId);
        return userIds.stream()
                .map(userId -> userRepository.findByUserId(userId).get())
                .collect(Collectors.collectingAndThen(Collectors.toList(), Students::of));
    }
}
