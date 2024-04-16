package nextstep.courses.infrastructure;

import nextstep.courses.domain.student.SessionStudent;
import nextstep.courses.infrastructure.engine.SessionStudentRepository;
import nextstep.courses.infrastructure.util.LocalDateTimeConverter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.LocalTime.now;

@Repository("sessionStudentRepository")
public class JdbcSessionStudentRepository implements SessionStudentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionStudent student) {
        String sql = "insert into session_student (session_id, ns_user_id, enrollment_status, created_at) " +
                "values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, student.getSessionId(), student.getNsUserId(), student.getEnrollmentStatus().get(), now());
    }

    @Override
    public int[] updateAll(List<SessionStudent> students) {
        String sql = "update session_student " +
                    "set enrollment_status = ?, " +
                    "updated_at = ? " +
                    "where id = ?";
        return jdbcTemplate.batchUpdate(sql, batchUpdateStudentsSetter(students));
    }

    private BatchPreparedStatementSetter batchUpdateStudentsSetter(List<SessionStudent> students) {
        return new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                SessionStudent student = students.get(i);
                ps.setString(1, student.getEnrollmentStatus().get());
                ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                ps.setLong(3, student.getId());
            }

            @Override
            public int getBatchSize() {
                return students.size();
            }
        };
    }

    private List<Object[]> batchArgs(List<SessionStudent> students) {
        List<Object[]> batchArgs = new ArrayList<>();

        for (SessionStudent student : students) {
            Object[] args = new Object[]{
                    student.getId(),
                    student.getSessionId(),
                    student.getNsUserId(),
                    student.getEnrollmentStatus().get(),
                    student.getCreatedAt(),
                    now()
            };
            batchArgs.add(args);
        }
        return Collections.unmodifiableList(batchArgs);
    }

    @Override
    public List<SessionStudent> findAllBySessionId(Long sessionId) {
        String sql = "select id, session_id, ns_user_id, enrollment_status, created_at, updated_at " +
                "from session_student " +
                "where session_id = ?";

        return jdbcTemplate.query(sql, studentEntityMapper(), sessionId);
    }

    private RowMapper<SessionStudent> studentEntityMapper() {
        return (rs, rowNum) -> new SessionStudent(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getString(4),
                LocalDateTimeConverter.convert(rs.getTimestamp(5)),
                LocalDateTimeConverter.convert(rs.getTimestamp(6))
        );
    }

}
