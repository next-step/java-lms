package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository("sessionRepository")
public class JdbcPaySessionRepository implements PaySessionRepository {
    private final JdbcOperations jdbcTemplate;

    private final String sessionImageTableName = "pay_session_image";
    private final String studentsTableName = "pay_session_students";
    private final String approveStudentsTableName = "pay_session_approve_students";
    private final String sessionTableName = "pay_session";

    public JdbcPaySessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sessions findByCourseId(Long courseId) {
        String paySessionSql = "select id from pay_session where course_id = ?";

        List<Long> paySessionIds = jdbcTemplate.query(paySessionSql, ((rs, rowNum) -> rs.getLong(1)), courseId);

        Set<Session> sessions = paySessionIds.stream()
                .map(this::findBySessionId)
                .filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toSet());


        return new Sessions(sessions);
    }

    @Override
    public Optional<PaySession> findBySessionId(Long sessionId) {
        String paySessionSql = "select id, recruit_status, progress_status, amount, maximum_students, start_date, end_date from " + sessionTableName + " where id = ?";


        List<SessionImage> sessionImage = findSessionImageBySessionId(sessionId);
        Set<NsUser> students = findSessionStudentsBySessionId(sessionId);
        Set<NsUser> approveStudents = findSessionApproveStudentsBySessionId(sessionId);

        RowMapper<PaySession> sessionMapper = (rs, rowNum) ->
                new PaySession(rs.getLong(1),
                        sessionImage,
                        RecruitStatus.findByName(rs.getString(2)),
                        SessionProgressStatus.findByName(rs.getString(3)),
                        new SessionDate(toLocalDate(rs.getTimestamp(6)), toLocalDate(rs.getTimestamp(7))),
                        students,
                        approveStudents,
                        rs.getInt(5),
                        rs.getInt(4)
                );

        return Optional.ofNullable(jdbcTemplate.queryForObject(paySessionSql, sessionMapper, sessionId));

    }

    @Override
    public void saveSession(PaySession session, Long courseId) {
        String sql = "insert into " + sessionTableName + " (id, progress_status, recruit_status, amount, maximum_students, start_date, end_date, course_id) values (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                session.getId(),
                session.getSessionProgressStatus().name(),
                session.getRecruitStatus().name(),
                session.getAmount(),
                session.getMaximumStudents(),
                session.getSessionDate().getStartDate(),
                session.getSessionDate().getEndDate(),
                courseId);

        insertStudents(session);
        insertSessionImage(session);
        insertApproveStudents(session);
    }

    private List<SessionImage> findSessionImageBySessionId(Long sessionId) {
        String sessionImageSql = "select id, image_path, width, height, image_size from " + sessionImageTableName + " where session_id = ?";

        RowMapper<SessionImage> sessionImageMapper = (rs, rowNum) ->
                new SessionImage(rs.getLong(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));


        return jdbcTemplate.query(sessionImageSql, sessionImageMapper, sessionId);
    }

    private Set<NsUser> findSessionStudentsBySessionId(Long sessionId) {
        String studentsSql = "select ns_user.id, ns_user.user_id, ns_user.password, ns_user.name, ns_user.email, ns_user.created_at, ns_user.updated_at " +
                "from " + studentsTableName + " join ns_user on " + studentsTableName + ".student_id = ns_user.id " +
                "where " + studentsTableName + ".session_id = ?";

        RowMapper<NsUser> studentsMapper = (rs, rowNum) ->
                new NsUser(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        toLocalDateTime(rs.getTimestamp(6)),
                        toLocalDateTime(rs.getTimestamp(7)));

        return new HashSet<>(jdbcTemplate.query(studentsSql, studentsMapper, sessionId));
    }


    private Set<NsUser> findSessionApproveStudentsBySessionId(Long sessionId) {
        String studentsSql = "select ns_user.id, ns_user.user_id, ns_user.password, ns_user.name, ns_user.email, ns_user.created_at, ns_user.updated_at " +
                "from " + approveStudentsTableName + " join ns_user on " + approveStudentsTableName + ".student_id = ns_user.id " +
                "where " + approveStudentsTableName + ".session_id = ?";

        RowMapper<NsUser> studentsMapper = (rs, rowNum) ->
                new NsUser(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        toLocalDateTime(rs.getTimestamp(6)),
                        toLocalDateTime(rs.getTimestamp(7)));

        return new HashSet<>(jdbcTemplate.query(studentsSql, studentsMapper, sessionId));
    }

    private void insertStudents(Session session) {
        String sql = "insert into " + studentsTableName + " (session_id, student_id) values (?, ?)";

        session.getStudents().forEach(student ->
                jdbcTemplate.update(sql, session.getId(), student.getId()));
    }

    private void insertApproveStudents(Session session) {
        String sql = "insert into " + approveStudentsTableName + " (session_id, student_id) values (?, ?)";

        session.getApproveStudents().forEach(student ->
                jdbcTemplate.update(sql, session.getId(), student.getId()));
    }

    private void insertSessionImage(Session session) {
        String sql = "insert into " + sessionImageTableName + " (id, image_path, width, height, image_size, session_id) values (?, ?, ?, ?, ?, ?)";
        List<SessionImage> sessionImages = session.getSessionImage();

        sessionImages.forEach(sessionImage -> {
            SessionImageSize sessionImageSize = sessionImage.getSessionImageSize();
            jdbcTemplate.update(sql, sessionImage.getId(), sessionImage.getPath(), sessionImageSize.getWidth(), sessionImageSize.getHeight(), sessionImageSize.getSize(), session.getId());
        });

    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }
}
