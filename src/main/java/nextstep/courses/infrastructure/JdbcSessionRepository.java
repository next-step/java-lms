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
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sessions findByCourseId(Long courseId) {
        String paySessionSql = "select id from pay_session where course_id = ?";
        String freeSessionSql = "select id from free_session where course_id = ?";
        Set<Session> sessions = new HashSet<>();

        List<Long> paySessionIds = jdbcTemplate.query(paySessionSql, ((rs, rowNum) -> rs.getLong(1)), courseId);
        List<Long> freeSessionIds = jdbcTemplate.query(freeSessionSql, ((rs, rowNum) -> rs.getLong(1)), courseId);

        sessions.addAll(paySessionIds.stream()
                .map(id -> findBySessionId(id, PaySession.class))
                .filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toSet()));

        sessions.addAll(freeSessionIds.stream()
                .map(id -> findBySessionId(id, FreeSession.class))
                .filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toSet()));


        return new Sessions(sessions);
    }

    @Override
    public <T> Optional<Session> findBySessionId(Long sessionId, Class<T> type) {
        if (type.isAssignableFrom(PaySession.class)) {
            return findPaySessionById(sessionId);
        }
        if (type.isAssignableFrom(FreeSession.class)) {
            return findFreeSessionById(sessionId);
        }

        throw new NoSuchElementException();
    }

    private Optional<Session> findFreeSessionById(Long sessionId) {
        String paySessionSql = "select id, recruit_status, progress_status, start_date, end_date from free_session where id = ?";
        String sessionImageTableName = "free_session_image";
        String studentsTableName = "free_session_students";
        String approveStudentsTableName = "free_session_approve_students";

        List<SessionImage> sessionImage = findSessionImageBySessionId(sessionId, sessionImageTableName);
        Set<NsUser> students = findSessionStudentsBySessionId(sessionId, studentsTableName);
        Set<NsUser> approveStudents = findSessionApproveStudentsBySessionId(sessionId, approveStudentsTableName);

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

    private Optional<Session> findPaySessionById(Long sessionId) {
        String paySessionSql = "select id, recruit_status, progress_status, amount, maximum_students, start_date, end_date from pay_session where id = ?";
        String sessionImageTableName = "pay_session_image";
        String studentsTableName = "pay_session_students";
        String approveStudentsTableName = "pay_session_approve_students";

        List<SessionImage> sessionImage = findSessionImageBySessionId(sessionId, sessionImageTableName);
        Set<NsUser> students = findSessionStudentsBySessionId(sessionId, studentsTableName);
        Set<NsUser> approveStudents = findSessionApproveStudentsBySessionId(sessionId, approveStudentsTableName);

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
    public void saveSession(Session session, Long courseId) {
        if (session instanceof PaySession) {
            savePaySession((PaySession) session, courseId);
            return;
        }

        if (session instanceof FreeSession) {
            saveFreeSession((FreeSession) session, courseId);
            return;
        }

        throw new NoSuchElementException();
    }

    private void savePaySession(PaySession paySession, Long courseId) {
        String sql = "insert into pay_session (id, progress_status, recruit_status, amount, maximum_students, start_date, end_date, course_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
        String studentsTableName = "pay_session_students";
        String approveStudentsTableName = "pay_session_approve_students";
        String sessionImageTableName = "pay_session_image";

        jdbcTemplate.update(sql,
                paySession.getId(),
                paySession.getSessionProgressStatus().name(),
                paySession.getRecruitStatus().name(),
                paySession.getAmount(),
                paySession.getMaximumStudents(),
                paySession.getSessionDate().getStartDate(),
                paySession.getSessionDate().getEndDate(),
                courseId);

        insertStudents(paySession, studentsTableName);
        insertSessionImage(paySession, sessionImageTableName);
        insertApproveStudents(paySession, approveStudentsTableName);
    }

    private void saveFreeSession(FreeSession freeSession, Long courseId) {
        String sql = "insert into free_session (id, progress_status, recruit_status, start_date, end_date, course_id) values (?, ?, ?, ?, ?, ?)";
        String studentsTableName = "free_session_students";
        String approveStudentsTableName = "free_session_approve_students";
        String sessionImageTableName = "free_session_image";

        jdbcTemplate.update(sql,
                freeSession.getId(),
                freeSession.getSessionProgressStatus().name(),
                freeSession.getRecruitStatus().name(),
                freeSession.getSessionDate().getStartDate(),
                freeSession.getSessionDate().getEndDate(),
                courseId);

        insertStudents(freeSession, studentsTableName);
        insertSessionImage(freeSession, sessionImageTableName);
        insertApproveStudents(freeSession, approveStudentsTableName);
    }

    private List<SessionImage> findSessionImageBySessionId(Long sessionId, String tableName) {
        String sessionImageSql = "select id, image_path, width, height, image_size from " + tableName + " where session_id = ?";

        RowMapper<SessionImage> sessionImageMapper = (rs, rowNum) ->
                new SessionImage(rs.getLong(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));


        return jdbcTemplate.query(sessionImageSql, sessionImageMapper, sessionId);
    }

    private Set<NsUser> findSessionStudentsBySessionId(Long sessionId, String tableName) {
        String studentsSql = "select ns_user.id, ns_user.user_id, ns_user.password, ns_user.name, ns_user.email, ns_user.created_at, ns_user.updated_at " +
                "from " + tableName + " join ns_user on " + tableName + ".student_id = ns_user.id " +
                "where " + tableName +".session_id = ?";

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



    private Set<NsUser> findSessionApproveStudentsBySessionId(Long sessionId, String tableName) {
        String studentsSql = "select ns_user.id, ns_user.user_id, ns_user.password, ns_user.name, ns_user.email, ns_user.created_at, ns_user.updated_at " +
                "from " + tableName + " join ns_user on " + tableName + ".student_id = ns_user.id " +
                "where " + tableName +".session_id = ?";

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

    private void insertStudents(Session session, String tableName) {
        String sql = "insert into " + tableName + " (session_id, student_id) values (?, ?)";

        session.getStudents().forEach(student ->
                jdbcTemplate.update(sql, session.getId(), student.getId()));
    }

    private void insertApproveStudents(Session session, String tableName) {
        String sql = "insert into " + tableName + " (session_id, student_id) values (?, ?)";

        session.getApproveStudents().forEach(student ->
                jdbcTemplate.update(sql, session.getId(), student.getId()));
    }

    private void insertSessionImage(Session session, String tableName) {
        String sql = "insert into " + tableName + " (id, image_path, width, height, image_size, session_id) values (?, ?, ?, ?, ?, ?)";
        List<SessionImage> sessionImages = session.getSessionImage();

        sessionImages.forEach(sessionImage ->  {
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
