package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.*;
import nextstep.courses.domain.repository.StudentRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into student (session_id, ns_user_id, created_at) values(?, ?, ?)";
        return jdbcTemplate.update(sql, student.getSession().getId(), student.getNsUser().getId(), student.getCreatedAt());
    }

    @Override
    public Student findById(Long id) {
        String sql = "select s.id, s.created_at, s.updated_at," +
                "n.id ,n.course_id ,n.start_date ,n.end_date ,n.image_path ,n.image_file , n.status ,n.price,n.capacity ,n.creator_id ,n.created_at ,n.updated_at, " +
                "u.id , u.user_id, u.password, u.name, u.email, u.balance, u.created_at, u.updated_at " +
                "from student s " +
                "join session n on s.session_id = n.id " +
                "join ns_user u on s.ns_user_id = u.id " +
                "where s.id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> {
            try {
                return new Student(
                        rs.getLong(1),
                        new NsUser(
                                rs.getLong(16),
                                rs.getString(17),
                                rs.getString(18),
                                rs.getString(19),
                                rs.getString(20),
                                rs.getBigDecimal(21),
                                toLocalDateTime(rs.getTimestamp(22)),
                                toLocalDateTime(rs.getTimestamp(23))),
                        new Session(
                                rs.getLong(4),
                                rs.getLong(5),
                                new SessionPeriod(
                                        rs.getDate(6).toLocalDate().atStartOfDay(),
                                        rs.getDate(7).toLocalDate().atStartOfDay()
                                ),
                                new SessionImage(
                                        rs.getString(8),
                                        rs.getBlob(9) != null ? rs.getBlob(9).getBinaryStream().readAllBytes() : null
                                ),
                                SessionStatus.valueOf(rs.getString(10)),
                                rs.getLong(11),
                                rs.getInt(12),
                                rs.getLong(13),
                                toLocalDateTime(rs.getTimestamp(14)),
                                toLocalDateTime(rs.getTimestamp(15))),
                        toLocalDateTime(rs.getTimestamp(2)),
                        toLocalDateTime(rs.getTimestamp(3)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
