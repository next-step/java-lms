package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.image.Image;
import nextstep.courses.domain.course.image.ImageRepository;
import nextstep.courses.domain.course.session.*;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;
    private final ImageRepository imageRepository;
    private final ApplicantsRepository applicantsRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageRepository = new JdbcImageRepository(jdbcTemplate);
        this.applicantsRepository = new JdbcApplicantsRepository(jdbcTemplate);
    }

    @Override
    public Optional<Session> findById(Long id) {
        String sql = "select " +
                "id, image_id, start_date, end_date, session_type, amount, quota, " +
                "session_status, course_id, creator_id, created_at, updated_at " +
                "from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                findImageById(rs.getLong(2)),
                new Duration(
                        toLocalDate(rs.getTimestamp(3)),
                        toLocalDate(rs.getTimestamp(4))
                ),
                new SessionState(
                        SessionType.find(rs.getString(5)),
                        rs.getLong(6),
                        rs.getInt(7)
                ),
                findAllBySessionId(id),
                SessionStatus.find(rs.getString(8)),
                rs.getLong(10),
                toLocalDateTime(rs.getTimestamp(11)),
                toLocalDateTime(rs.getTimestamp(12)));
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public int save(Long courseId, Session session) {
        Duration duration = session.getDuration();
        SessionState sessionState = session.getSessionState();
        SessionType sessionType = sessionState.getSessionType();
        SessionStatus status = session.getSessionStatus();
        Image image = session.getImage();
        String sql = "insert into session " +
                        "(start_date, end_date, session_type, session_status, amount, " +
                        "quota, image_id, course_id, creator_id, created_at, updated_at) " +
                        "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, duration.getStartDate(), duration.getEndDate(), sessionType.name(),
                status.name(), sessionState.getAmount(), sessionState.getQuota(), image.getId(), courseId,
                session.getCreatorId(), session.getCreatedAt(), session.getUpdatedAt());
    }

    @Override
    public int saveApply(Apply apply) {
        String sql = "insert into apply " +
                "(session_id, ns_user_id, creator_id, created_at, updated_at) " +
                "values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, apply.getSessionId(), apply.getNsUserId(),
                apply.getCreatorId(), apply.getCreatedAt(), apply.getUpdatedAt());
    }

    @Override
    public Optional<Apply> findApplyByIds(Long nsUserId, Long sessionId) {
        String sql = "select " +
                "session_id, ns_user_id, creator_id, created_at, updated_at " +
                "from apply where ns_user_id = ? and session_id = ?";
        RowMapper<Apply> rowMapper = (rs, rowNum) -> new Apply(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)));
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, nsUserId, sessionId));
    }

    @Override
    public int update(Long sessionId, Session session) {
        Duration duration = session.getDuration();
        SessionState sessionState = session.getSessionState();
        SessionType sessionType = sessionState.getSessionType();
        SessionStatus sessionStatus = session.getSessionStatus();
        String sql = "update session set " +
                "start_date = ?, end_date = ?, session_type = ?, amount = ?, quota = ?, session_status = ? " +
                "where id = ?";
        return jdbcTemplate.update(sql, duration.getStartDate(), duration.getEndDate(), sessionType.name(),
                sessionState.getAmount(), sessionState.getQuota(), sessionStatus.name(), sessionId);
    }

    @Override
    public Sessions findAllByCourseId(Long courseId) {
        String sql = "select " +
                "id, image_id, start_date, end_date, session_type, amount, quota, " +
                "session_status, course_id, creator_id, created_at, updated_at " +
                "from session where course_id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                findImageById(rs.getLong(2)),
                new Duration(
                        toLocalDate(rs.getTimestamp(3)),
                        toLocalDate(rs.getTimestamp(4))
                ),
                new SessionState(
                        SessionType.find(rs.getString(5)),
                        rs.getLong(6),
                        rs.getInt(7)
                ),
                findAllBySessionId(rs.getLong(1)),
                SessionStatus.find(rs.getString(8)),
                rs.getLong(10),
                toLocalDateTime(rs.getTimestamp(11)),
                toLocalDateTime(rs.getTimestamp(12)));

        List<Session> sessions = jdbcTemplate.query(sql, rowMapper, courseId);
        return new Sessions(sessions);
    }

    @Override
    public int updateCourse(Long courseId, Session session) {
        String sql = "update session set course_id = ? where id = ?";
        return jdbcTemplate.update(sql, session.getId(), courseId);
    }

    private Image findImageById(Long id) {
        return this.imageRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    private Applicants findAllBySessionId(Long id) {
        return this.applicantsRepository.findAllBySessionId(id);
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
