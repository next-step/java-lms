package nextstep.session.infrastructure;

import nextstep.session.domain.Student;
import nextstep.session.domain.StudentRepository;
import nextstep.session.dto.StudentVO;
import nextstep.utils.DbTimestampUtils;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<StudentVO> findBySessionId(long sessionId) {
        String sql = "select id, session_id, ns_user_id, deleted, created_at, last_modified_at from student where session_id = ? and deleted = false";
        RowMapper<StudentVO> rowMapper = (rs, rowNum) -> new StudentVO(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getBoolean(4),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp(5)),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp(6)));
        return jdbcTemplate.query(sql, new Object[]{sessionId}, rowMapper);
    }

    @Override
    public long save(Student student) {
        String sql = "insert into student (session_id, ns_user_id, deleted, created_at, last_modified_at) values(?, ?, ?, ?, ?)";
        StudentVO studentVO = student.toVO();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, studentVO.getSessionId());
            ps.setString(2, studentVO.getUserId());
            ps.setBoolean(3, studentVO.isDeleted());
            ps.setTimestamp(4, DbTimestampUtils.toTimestamp(studentVO.getCreatedAt()));
            ps.setTimestamp(5, DbTimestampUtils.toTimestamp(studentVO.getLastModifiedAt()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public int updateDeleteStatus(long sessionId, String studentId, boolean deleteStatus) {
        String sql = "UPDATE student SET deleted = ? WHERE session_id = ? AND ns_user_id = ?";
        return jdbcTemplate.update(sql, deleteStatus, sessionId, studentId);
    }
}
