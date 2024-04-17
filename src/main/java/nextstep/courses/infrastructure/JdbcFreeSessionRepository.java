package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository("sessionRepository")
public class JdbcFreeSessionRepository implements FreeSessionRepository {
    private final JdbcOperations jdbcTemplate;

    private final String sessionImageTableName = "free_session_image";
    private final String studentsTableName = "free_session_students";
    private final String approveStudentsTableName = "free_session_approve_students";
    private final String sessionTableName = "free_session";

    public JdbcFreeSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sessions findByCourseId(Long courseId) {
        String freeSessionSql = "select id from " + sessionTableName + " where course_id = ?";

        List<Long> freeSessionIds = jdbcTemplate.query(freeSessionSql, ((rs, rowNum) -> rs.getLong(1)), courseId);

        Set<Session> sessions = freeSessionIds.stream()
                .map(this::findBySessionId)
                .filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toSet());


        return new Sessions(sessions);
    }

    @Override
    public Optional<FreeSession> findBySessionId(Long sessionId) {
        String paySessionSql = "select id, recruit_status, progress_status, start_date, end_date from " + sessionTableName + " where id = ?";


        List<SessionImage> sessionImage = findSessionImageBySessionId(sessionId);
        Set<NsUser> students = findSessionStudentsBySessionId(sessionId);
        Set<NsUser> approveStudents = findSessionApproveStudentsBySessionId(sessionId);

        RowMapper<FreeSession> sessionMapper = (rs, rowNum) ->
                new FreeSession(rs.getLong(1),
                        sessionImage,
                        RecruitStatus.findByName(rs.getString(2)),
                        SessionProgressStatus.findByName(rs.getString(3)),
                        new SessionDate(toLocalDate(rs.getTimestamp(4)), toLocalDate(rs.getTimestamp(5))),
                        students,
                        approveStudents
                );

        return Optional.ofNullable(jdbcTemplate.queryForObject(paySessionSql, sessionMapper, sessionId));

    }

    @Override
    public void saveSession(FreeSession session, Long courseId) {
        String sql = "insert into " + sessionTableName + " (id, progress_status, recruit_status, start_date, end_date, course_id) values (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                session.getId(),
                session.getSessionProgressStatus().name(),
                session.getRecruitStatus().name(),
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
