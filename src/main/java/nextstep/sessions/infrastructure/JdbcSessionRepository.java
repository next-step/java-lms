package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionCoverImage;
import nextstep.sessions.domain.SessionDuration;
import nextstep.sessions.domain.SessionPaymentType;
import nextstep.sessions.domain.SessionRecruitmentStatus;
import nextstep.sessions.domain.SessionRegistration;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStatus;
import nextstep.students.domain.StudentRepository;
import nextstep.students.domain.Students;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;
    private final StudentRepository studentRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, StudentRepository studentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.studentRepository = studentRepository;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into " +
                "session (id, course_id, started_at, ended_at, cover_image, payment_type, status, recruitment_status, student_capacity, created_at) " +
                "values(?, ?, ?, ?, ?, ?, ? ,? ,?, ?)";
        return jdbcTemplate.update(sql,
                session.getId(),
                session.getCourseId(),
                session.getStartedAt(),
                session.getEndedAt(),
                session.getCoverImageUrl(),
                session.getPaymentTypeName(),
                session.getStatusName(),
                session.geRecruitmentStatusName(),
                session.getStudentCapacity(),
                session.getCreatedAt());
    }

    @Override
    public Optional<Session> findById(Long id) {
        String sql = "select id, course_id," +
                "started_at, ended_at, cover_image, payment_type, status, recruitment_status, student_capacity, created_at, updated_at " +
                "from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getLong(2),
                new SessionDuration(
                        toLocalDateTime(rs.getTimestamp(3)),
                        toLocalDateTime(rs.getTimestamp(4))),
                SessionCoverImage.create(rs.getString(5)),
                SessionPaymentType.find(rs.getString(6)),
                new SessionRegistration(
                        SessionStatus.find(rs.getString(7)),
                        SessionRecruitmentStatus.find(rs.getString(8)),
                        new Students(studentRepository.findAllBySessionId(rs.getLong(1))),
                        rs.getInt(9)),
                toLocalDateTime(rs.getTimestamp(10)),
                toLocalDateTime(rs.getTimestamp(11)));
        Session savedSession = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return Optional.ofNullable(savedSession);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

}
