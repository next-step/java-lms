package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.session.*;
import nextstep.courses.domain.course.session.apply.Applies;
import nextstep.courses.domain.course.session.apply.ApplyRepository;
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
    private final ApplyRepository applyRepository;
    KeyHolder keyHolder = new GeneratedKeyHolder();

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageRepository = new JdbcImageRepository(jdbcTemplate);
        this.applyRepository = new JdbcApplyRepository(jdbcTemplate);
    }

    @Override
    public Optional<Session> findById(Long id) {
        String sql = "select " +
                "id, start_date, end_date, session_type, amount, quota, recruit_status, " +
                "session_status, course_id, creator_id, created_at, updated_at " +
                "from session where id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                findAllImagesBySessionId(rs.getLong(1)),
                findAllAppliesBySessionId(rs.getLong(1)),
                new SessionDuration(
                        rs.getTimestamp(2).toLocalDateTime().toLocalDate(),
                        rs.getTimestamp(3).toLocalDateTime().toLocalDate()
                ),
                new SessionState(
                        SessionType.find(rs.getString(4)),
                        rs.getLong(5),
                        rs.getInt(6)
                ),
                SessionRecruitStatus.find(rs.getString(7)),
                SessionProgressStatus.find(rs.getString(8)),
                rs.getLong(10),
                rs.getTimestamp(11).toLocalDateTime(),
                toLocalDateTime(rs.getTimestamp(12)));
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public Session save(Long courseId, Session session) {
        Session savedSession = saveSession(courseId, session);

        List<Image> images = new ArrayList<>();
        for (Image image : session.images()) {
            Image savedImage = imageRepository.save(savedSession.id(), image);
            images.add(savedImage);
        }

        return new Session(savedSession.id(), new Images(images), new Applies(), session.sessionDetail(),
                session.creatorId(), session.createdAt(), session.updatedAt());
    }

    private Session saveSession(Long courseId, Session session) {
        SessionDuration sessionDuration = session.sessionDuration();
        SessionRecruitStatus sessionRecruitStatus = session.sessionRecruitStatus();
        SessionState sessionState = session.sessionState();
        SessionType sessionType = sessionState.sessionType();
        SessionProgressStatus sessionProgressStatus = session.sessionProgressStatus();
        String sql = "insert into session " +
                "(start_date, end_date, session_type, session_status, amount, " +
                "recruit_status, quota, course_id, creator_id, created_at, updated_at) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(sessionDuration.startDate().atStartOfDay()));
            ps.setTimestamp(2, Timestamp.valueOf(sessionDuration.endDate().atStartOfDay()));
            ps.setString(3, sessionType.name());
            ps.setString(4, sessionProgressStatus.name());
            ps.setLong(5, sessionState.amount());
            ps.setString(6, sessionRecruitStatus.name());
            ps.setInt(7, sessionState.quota());
            ps.setLong(8, courseId);
            ps.setLong(9, session.creatorId());
            ps.setTimestamp(10, Timestamp.valueOf(session.createdAt()));
            ps.setTimestamp(11, toTimeStamp(session.updatedAt()));
            return ps;
        }, keyHolder);

        Long sessionId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return new Session(sessionId, new Images(), new Applies(), session.sessionDetail(),
                session.creatorId(), session.createdAt(), session.updatedAt());
    }

    @Override
    public int update(Long sessionId, Session session) {
        SessionDuration sessionDuration = session.sessionDuration();
        SessionRecruitStatus sessionRecruitStatus = session.sessionRecruitStatus();
        SessionState sessionState = session.sessionState();
        SessionType sessionType = sessionState.sessionType();
        SessionProgressStatus sessionProgressStatus = session.sessionProgressStatus();
        String sql = "update session set " +
                "start_date = ?, end_date = ?, session_type = ?, recruit_status = ?, amount = ?, quota = ?, session_status = ? " +
                "where id = ?";
        return jdbcTemplate.update(sql, sessionDuration.startDate(), sessionDuration.endDate(), sessionType.name(),
                sessionRecruitStatus.name(), sessionState.amount(), sessionState.quota(), sessionProgressStatus.name(), sessionId);
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
                findAllAppliesBySessionId(rs.getLong(1)),
                new SessionDuration(
                        rs.getTimestamp(2).toLocalDateTime().toLocalDate(),
                        rs.getTimestamp(3).toLocalDateTime().toLocalDate()
                ),
                new SessionState(
                        SessionType.find(rs.getString(4)),
                        rs.getLong(5),
                        rs.getInt(6)
                ),
                SessionRecruitStatus.find(rs.getString(8)),
                SessionProgressStatus.find(rs.getString(9)),
                rs.getLong(10),
                rs.getTimestamp(11).toLocalDateTime(),
                toLocalDateTime(rs.getTimestamp(12)));

        List<Session> sessions = jdbcTemplate.query(sql, rowMapper, courseId);
        return new Sessions(sessions);
    }

    @Override
    public int updateCourseId(Long courseId, Session session) {
        String sql = "update session set course_id = ? where id = ?";
        return jdbcTemplate.update(sql, session.id(), courseId);
    }

    private Images findAllImagesBySessionId(Long id) {
        return this.imageRepository.findAllBySessionId(id);
    }

    private Applies findAllAppliesBySessionId(Long id) {
        return this.applyRepository.findAllBySessionId(id);
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
