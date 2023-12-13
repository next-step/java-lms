package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.session.*;
import nextstep.courses.domain.course.session.image.Image;
import nextstep.courses.domain.course.session.image.ImageRepository;
import nextstep.courses.domain.course.session.image.Images;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;
    private final ImageRepository imageRepository;
    private final ApplicantsRepository applicantsRepository;
    KeyHolder keyHolder = new GeneratedKeyHolder();

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageRepository = new JdbcImageRepository(jdbcTemplate);
        this.applicantsRepository = new JdbcApplicantsRepository(jdbcTemplate);
    }

    @Override
    public Optional<Session> findById(Long id) {
        String sql = "select " +
                "id, start_date, end_date, session_type, amount, quota, recruit_status " +
                "session_status, course_id, creator_id, created_at, updated_at " +
                "from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                findAllImagesBySessionId(rs.getLong(1)),
                new Duration(
                        rs.getTimestamp(2).toLocalDateTime().toLocalDate(),
                        rs.getTimestamp(3).toLocalDateTime().toLocalDate()
                ),
                new SessionState(
                        SessionType.find(rs.getString(4)),
                        rs.getLong(5),
                        rs.getInt(6)
                ),
                findAllBySessionId(id),
                RecruitStatus.find(rs.getString(7)),
                SessionStatus.find(rs.getString(8)),
                rs.getLong(10),
                rs.getTimestamp(11).toLocalDateTime(),
                toLocalDateTime(rs.getTimestamp(12)));
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public Session save(Long courseId, Session session) {
        Session savedSession = saveSession(courseId, session);

        List<Image> images = new ArrayList<>();
        for (Image image : session.getImages()) {
            Image savedImage = imageRepository.save(savedSession.getId(), image);
            images.add(savedImage);
        }

        savedSession.setImages(new Images(images));
        return savedSession;
    }

    private Session saveSession(Long courseId, Session session) {
        Duration duration = session.getDuration();
        RecruitStatus recruitStatus = session.getRecruitStatus();
        SessionState sessionState = session.getSessionState();
        SessionType sessionType = sessionState.getSessionType();
        SessionStatus status = session.getSessionStatus();
        String sql = "insert into session " +
                "(start_date, end_date, session_type, session_status, amount, " +
                "recruit_status, quota, course_id, creator_id, created_at, updated_at) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(duration.getStartDate().atStartOfDay()));
            ps.setTimestamp(2, Timestamp.valueOf(duration.getEndDate().atStartOfDay()));
            ps.setString(3, sessionType.name());
            ps.setString(4, status.name());
            ps.setLong(5, sessionState.getAmount());
            ps.setString(6, recruitStatus.name());
            ps.setInt(7, sessionState.getQuota());
            ps.setLong(8, courseId);
            ps.setLong(9, session.getCreatorId());
            ps.setTimestamp(10, Timestamp.valueOf(session.getCreatedAt()));
            ps.setTimestamp(11, toTimeStamp(session.getUpdatedAt()));
            return ps;
        }, keyHolder);

        Long sessionId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        session.setId(sessionId);

        return session;
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
                rs.getTimestamp(4).toLocalDateTime(),
                toLocalDateTime(rs.getTimestamp(5)));
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, nsUserId, sessionId));
    }

    @Override
    public int update(Long sessionId, Session session) {
        Duration duration = session.getDuration();
        RecruitStatus recruitStatus = session.getRecruitStatus();
        SessionState sessionState = session.getSessionState();
        SessionType sessionType = sessionState.getSessionType();
        SessionStatus sessionStatus = session.getSessionStatus();
        String sql = "update session set " +
                "start_date = ?, end_date = ?, session_type = ?, recruit_status = ?, amount = ?, quota = ?, session_status = ? " +
                "where id = ?";
        return jdbcTemplate.update(sql, duration.getStartDate(), duration.getEndDate(), sessionType.name(),
                recruitStatus.name(), sessionState.getAmount(), sessionState.getQuota(), sessionStatus.name(), sessionId);
    }

    @Override
    public Sessions findAllByCourseId(Long courseId) {
        String sql = "select " +
                "id, start_date, end_date, session_type, amount, quota, " +
                "recruit_status, session_status, course_id, creator_id, created_at, updated_at " +
                "from session where course_id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                findAllImagesBySessionId(rs.getLong(1)),
                new Duration(
                        rs.getTimestamp(2).toLocalDateTime().toLocalDate(),
                        rs.getTimestamp(3).toLocalDateTime().toLocalDate()
                ),
                new SessionState(
                        SessionType.find(rs.getString(4)),
                        rs.getLong(5),
                        rs.getInt(6)
                ),
                findAllBySessionId(rs.getLong(7)),
                RecruitStatus.find(rs.getString(8)),
                SessionStatus.find(rs.getString(9)),
                rs.getLong(10),
                rs.getTimestamp(11).toLocalDateTime(),
                toLocalDateTime(rs.getTimestamp(12)));

        List<Session> sessions = jdbcTemplate.query(sql, rowMapper, courseId);
        return new Sessions(sessions);
    }

    @Override
    public int updateCourse(Long courseId, Session session) {
        String sql = "update session set course_id = ? where id = ?";
        return jdbcTemplate.update(sql, session.getId(), courseId);
    }

    private Images findAllImagesBySessionId(Long id) {
        return this.imageRepository.findAllBySessionId(id);
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

    private Timestamp toTimeStamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Timestamp.valueOf(localDateTime);
    }
}
