package nextstep.users.infrastructure;

import nextstep.users.domain.NsStudent;
import nextstep.users.domain.NsStudentRepository;
import nextstep.users.domain.StudentStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("nsStudentRepository")
public class JdbcNsStudentRepository implements NsStudentRepository {

    private JdbcOperations jdbcTemplate;
    private JdbcUserRepository userRepository;

    public JdbcNsStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = new JdbcUserRepository(jdbcTemplate);
    }

    @Override
    public int[] save(NsStudent nsStudent) {
        int savedUserId = userRepository.save(nsStudent);
        List<Object[]> batchArgs = new ArrayList<>();
        Map<Long, StudentStatus> map = nsStudent.sessionEnrollment();

        for (Map.Entry<Long, StudentStatus> longStudentStatusEntry : map.entrySet()) {

            Object[] params = new Object[]{
                    savedUserId,
                    longStudentStatusEntry.getKey(),
                    longStudentStatusEntry.getValue().toString(),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            };

            batchArgs.add(params);
        }

        String sql = "insert into ns_student (nsUser_id, session_id, enrollment, created_at, updated_at) values (?,?,?,?,?)";
        return jdbcTemplate.batchUpdate(sql, batchArgs);
    }


//    @Override
//    public Optional<NsStudent> findByUserId(String userId) {
//        String sql = "select u.id, u.user_id, u.password, u.name, u.email, u.created_at, u.updated_at, s.session_id, s.enrollment " +
//                "from ns_user as u join ns_student as s on join u.id = s.nsUser_id" +
//                "where u.user_id = ?";
//        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
//                rs.getLong(1),
//                rs.getString(2),
//                rs.getString(3),
//                rs.getString(4),
//                rs.getString(5),
//                toLocalDateTime(rs.getTimestamp(6)),
//                toLocalDateTime(rs.getTimestamp(7)));
//        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, userId));
//    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
