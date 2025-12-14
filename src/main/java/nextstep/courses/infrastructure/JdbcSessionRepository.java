package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.FreeSessionType;
import nextstep.courses.domain.session.PaidSessionType;
import nextstep.courses.domain.session.ProgressStatus;
import nextstep.courses.domain.session.RecruitmentStatus;
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

        SessionType type = session.getSessionType();

        String sessionTypeStr = type.isFree() ? "FREE" : "PAID";
        Integer maxCapacity = null;
        Long fee = null;

        if (!type.isFree()) {
            PaidSessionType paidType = (PaidSessionType) type;
            maxCapacity = paidType.getMaxCapacity();
            fee = paidType.getFee();
        }

        String statusValue;
        String progressStatusValue = null;
        String recruitmentStatusValue = null;

        if (session.getProgressStatus() != null && session.getRecruitmentStatus() != null) {
            progressStatusValue = session.getProgressStatus().getValue();
            recruitmentStatusValue = session.getRecruitmentStatus().getValue();
            statusValue = session.getRecruitmentStatus().canEnroll() ? "모집중" : session.getProgressStatus().getValue();
        } else {
            statusValue = session.getStatus().getValue();
        }

        String sql = "insert into session (course_id, cohort, start_date, end_date, image_id, status, progress_status, recruitment_status, session_type, max_capacity, fee, created_at)" +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        jdbcTemplate.update(sql,
                courseId,
                session.getCohort(),
                Date.valueOf(session.getStartDate()),
                Date.valueOf(session.getEndDate()),
                imageId,
                statusValue,
                progressStatusValue,
                recruitmentStatusValue,
                sessionTypeStr,
                maxCapacity,
                fee,
                Timestamp.valueOf(LocalDateTime.now()));

    }

    @Override
    public Sessions findByCourseId(Long courseId) {
        String sql = "select s.id, s.cohort, s.start_date, s.end_date, s.status, s.progress_status, s.recruitment_status " +
                "s.session_type, s.max_capacity, s.fee, " +
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

            SessionType type = "FREE".equals(rs.getString("session_type"))
                    ? new FreeSessionType()
                    : new PaidSessionType(rs.getInt("max_capacity"), rs.getLong("fee"));

            String progressStatusValue = rs.getString("progress_status");
            String recruitmentStatusValue = rs.getString("recruitment_status");
            if (progressStatusValue != null && recruitmentStatusValue != null) {
                return new Session(
                        rs.getLong("id"),
                        rs.getInt("cohort"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        image,
                        ProgressStatus.from(progressStatusValue),
                        RecruitmentStatus.from(recruitmentStatusValue),
                        type);
            }
            SessionStatus status = SessionStatus.from(rs.getString("status"));
            return new Session(
                    rs.getLong("id"),
                    rs.getInt("cohort"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    image,
                    status,
                    type);
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

            return new Session(
                    rs.getLong("id"),
                    rs.getInt("cohort"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    image,
                    status,
                    type);
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
