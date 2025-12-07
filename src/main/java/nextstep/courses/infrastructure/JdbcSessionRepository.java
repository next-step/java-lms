package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.FreeSessionType;
import nextstep.courses.domain.session.PaidSessionType;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.SessionType;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.domain.session.image.SessionImage;
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
        Long imageId = saveSessionImage(session.getImage());

        Enrollment enrollment = session.getEnrollment();
        SessionStatus status = enrollment.getStatus();
        SessionType type = enrollment.getSessionType();

        String sessionTypeStr = type.isFree() ? "FREE" : "PAID";
        Integer maxCapacity = null;
        Long fee = null;

        if (!type.isFree()) {
            PaidSessionType paidType = (PaidSessionType) type;
            maxCapacity = paidType.getMaxCapacity();
            fee = paidType.getFee();
        }

        String sql = "insert into session (course_id, cohort, start_date, end_date, image_id, status, session_type, max_capacity, fee, created_at) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                courseId,
                session.getCohort(),
                Date.valueOf(session.getStartDate()),
                Date.valueOf(session.getEndDate()),
                imageId,
                status.getValue(),
                sessionTypeStr,
                maxCapacity,
                fee,
                Timestamp.valueOf(LocalDateTime.now()));

    }

    @Override
    public Sessions findByCourseId(Long courseId) {
        String sql = "select s.id, s.cohort, s.start_date, s.end_date, s.status, s.session_type, s.max_capacity, s.fee, " +
                "i.file_size, i.image_type, i.width, i.height " +
                "from session s " +
                "join session_image i on s.image_id = i.id " +
                "where s.course_id = ? " +
                "order by s.cohort";

        List<Session> sessionList = jdbcTemplate.query(sql, (rs, rowNum) -> {
            SessionImage image = new SessionImage(
                    rs.getLong("file_size"),
                    rs.getString("image_type"),
                    rs.getInt("width"),
                    rs.getInt("height"));

            SessionStatus status = SessionStatus.from(rs.getString("status"));
            SessionType type = "FREE".equals(rs.getString("session_type"))
                    ? new FreeSessionType()
                    : new PaidSessionType(rs.getInt("max_capacity"), rs.getLong("fee"));

            return new Session(
                    rs.getInt("cohort"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    image,
                    new Enrollment(status, type));
        }, courseId);

        return new Sessions(sessionList);
    }

    @Override
    public Session findById(Long sessionId) {
        String sql = "select s.id, s.cohort, s.start_date, s.end_date, s.status, s.session_type, s.max_capacity, s.fee, " +
                "i.file_size, i.image_type, i.width, i.height " +
                "from session s " +
                "join session_image i on s.image_id = i.id " +
                "where s.id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            SessionImage image = new SessionImage(
                    rs.getLong("file_size"),
                    rs.getString("image_type"),
                    rs.getInt("width"),
                    rs.getInt("height"));

            SessionStatus status = SessionStatus.from(rs.getString("status"));
            SessionType type = "FREE".equals(rs.getString("session_type"))
                    ? new FreeSessionType()
                    : new PaidSessionType(rs.getInt("max_capacity"), rs.getLong("fee"));

            Enrollment enrollment = new Enrollment(status, type);

            return new Session(
                    rs.getLong("id"),
                    rs.getInt("cohort"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    image,
                    enrollment);
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
