package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.FreeSessionType;
import nextstep.courses.domain.session.PaidSessionType;
import nextstep.courses.domain.session.ProgressStatus;
import nextstep.courses.domain.session.RecruitmentStatus;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionInfo;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionType;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.domain.image.SessionImage;
import nextstep.courses.domain.image.SessionImages;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Long courseId, Session session) {
        Long sessionId = saveSession(courseId, session);
        saveCoverImages(sessionId, session.getImages());
    }

    private Long saveSession(Long courseId, Session session) {
        Long imageId = saveSessionImage(session.getImage());
        String sql = "insert into session (course_id, cohort, start_date, end_date, image_id, status, progress_status, recruitment_status, session_type, max_capacity, fee, created_at)" +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, courseId);
            ps.setInt(2, session.getCohort());
            ps.setDate(3, Date.valueOf(session.getStartDate()));
            ps.setDate(4, Date.valueOf(session.getEndDate()));
            ps.setLong(5, imageId);
            ps.setString(6, getStatusValue(session));
            ps.setString(7, session.getProgressStatus().getValue());
            ps.setString(8, session.getRecruitmentStatus().getValue());
            ps.setString(9, getSessionTypeString(session.getSessionType()));
            ps.setObject(10, getMaxCapacity(session.getSessionType()), java.sql.Types.INTEGER);
            ps.setObject(11, getFee(session.getSessionType()), java.sql.Types.BIGINT);
            ps.setTimestamp(12, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private void saveCoverImages(Long sessionId, SessionImages images) {
        for (SessionImage image : images.getImages()) {
            Long imageId = saveSessionImage(image);
            String sql = "insert into session_cover_images (session_id, session_image_id) values(?, ?)";
            jdbcTemplate.update(sql, sessionId, imageId);
        }
    }

    private String getStatusValue(Session session) {
        return session.getRecruitmentStatus().canEnroll() ? "모집중" : session.getProgressStatus().getValue();


    }

    private String getSessionTypeString(SessionType sessionType) {
        return sessionType.isFree() ? "FREE" : "PAID";
    }


    private Integer getMaxCapacity(SessionType sessionType) {
        if (sessionType.isFree()) {
            return null;
        }
        return ((PaidSessionType) sessionType).getMaxCapacity();
    }

    private Long getFee(SessionType sessionType) {
        if (sessionType.isFree()) {
            return null;
        }
        return ((PaidSessionType) sessionType).getFee();
    }

    @Override
    public Sessions findByCourseId(Long courseId) {
        String sql = "select s.id, s.cohort, s.start_date, s.end_date, s.image_id, s.progress_status, s.recruitment_status " +
                "s.session_type, s.max_capacity, s.fee, " +
                "from session s " +
                "where s.course_id = ? " +
                "order by s.cohort";

        List<Session> sessionList = jdbcTemplate.query(sql, (rs, rowNum) -> {

            Long sessionId = rs.getLong("id");
            Long imageId = rs.getLong("image_id");
            SessionImages images = findSessionImages(sessionId, imageId);

            SessionInfo info = new SessionInfo(
                    rs.getInt("cohort"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    images);

            SessionType type = "FREE".equals(rs.getString("session_type"))
                    ? new FreeSessionType()
                    : new PaidSessionType(rs.getInt("max_capacity"), rs.getLong("fee"));

            ProgressStatus progressStatus = ProgressStatus.from(rs.getString("progress_status"));
            RecruitmentStatus recruitmentStatus = RecruitmentStatus.from(rs.getString("recruitment_status"));

            return new Session(info, progressStatus, recruitmentStatus, type);
        }, courseId);

        return new Sessions(sessionList);
    }

    @Override
    public Session findById(Long sessionId) {
        String sql = "select s.id, s.cohort, s.start_date, s.end_date, s.image_id, s.session_type, s.max_capacity, s.fee, " +
                "from session s " +
                "where s.id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Long id = rs.getLong("id");
            Long imageId = rs.getLong("image_id");
            SessionImages images = findSessionImages(id, imageId);

            SessionInfo info = new SessionInfo(
                    rs.getInt("cohort"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    images);

            ProgressStatus progressStatus = ProgressStatus.from(rs.getString("progress_status"));
            RecruitmentStatus recruitmentStatus = RecruitmentStatus.from(rs.getString("recruitment_status"));

            SessionType type = "FREE".equals(rs.getString("session_type"))
                    ? new FreeSessionType()
                    : new PaidSessionType(rs.getInt("max_capacity"), rs.getLong("fee"));

            return new Session(id, info, progressStatus, recruitmentStatus, type);
        }, sessionId);
    }

    private Long saveSessionImage(SessionImage image) {
        Long existingImageId = findSessionImageId(image);
        if (existingImageId != null) {
            return existingImageId;
        }

        String sql = "insert into session_image (file_size, image_type, width, height) values(?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, image.getFileSize());
            ps.setString(2, image.getImageTypeValue());
            ps.setInt(3, image.getWidth());
            ps.setInt(4, image.getHeight());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private SessionImages findSessionImages(Long sessionId, Long imageId) {
        String sql = "select si.file_size, si.image_type, si.width, si.height " +
                "from session_cover_images sci " +
                "join session_image si on sci.session_image_id = si.id " +
                "where sci.session_id = ? " +
                "order by sci.id";

        List<SessionImage> imageList = jdbcTemplate.query(sql,
                (rs, rowNum) -> new SessionImage(
                        rs.getLong("file_size"),
                        rs.getString("image_type"),
                        rs.getInt("width"),
                        rs.getInt("height")),
                sessionId);

        if (!imageList.isEmpty()) {
            return new SessionImages(imageList);
        }

        return new SessionImages(findSessionImageById(imageId));
    }

    private SessionImage findSessionImageById(Long imageId) {
        String sql = "select file_size, image_type, width, height from session_image where id = ?";
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new SessionImage(
                        rs.getLong("file_size"),
                        rs.getString("image_type"),
                        rs.getInt("width"),
                        rs.getInt("height")),
                imageId);
    }

    private Long findSessionImageId(SessionImage image) {
        String sql = "select id from session_image where file_size = ? and image_type = ? and width = ? and height = ?";
        List<Long> results = jdbcTemplate.query(sql,
                (rs, rowNum) -> rs.getLong("id"),
                image.getFileSize(),
                image.getImageTypeValue(),
                image.getWidth(),
                image.getHeight());
        return results.isEmpty() ? null : results.get(0);
    }
}
