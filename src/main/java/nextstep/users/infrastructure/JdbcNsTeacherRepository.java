package nextstep.users.infrastructure;

import nextstep.users.domain.NsTeacher;
import nextstep.users.domain.NsTeacherRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.StudentStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository("nsTeacherRepository")
public class JdbcNsTeacherRepository implements NsTeacherRepository {

    private JdbcOperations jdbcTemplate;
    private JdbcUserRepository userRepository;

    public JdbcNsTeacherRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = new JdbcUserRepository(jdbcTemplate);
    }

    @Override
    public int[] save(NsTeacher nsTeacher) {
        int savedUserId = userRepository.save(nsTeacher);
        List<Object[]> batchArgs = new ArrayList<>();
        Set<Long> sessionIdSet = nsTeacher.getSessionList();

        for (Long sessionId : sessionIdSet) {
            Object[] params = new Object[]{
                    savedUserId,
                    sessionId,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            };
            batchArgs.add(params);
        }

        String sql = "insert into ns_teacher (nsUser_id, session_id, created_at, updated_at) values (?,?,?,?)";
        return jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
