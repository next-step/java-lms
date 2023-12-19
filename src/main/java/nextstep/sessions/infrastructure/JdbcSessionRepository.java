package nextstep.sessions.infrastructure;

import nextstep.common.Period;
import nextstep.sessions.domain.ImageType;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionCharge;
import nextstep.sessions.domain.SessionImage;
import nextstep.sessions.domain.SessionImages;
import nextstep.sessions.domain.SessionProgressStatus;
import nextstep.sessions.domain.SessionRecruitmentStatus;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionStudent;
import nextstep.sessions.domain.SessionStudents;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(Session session) {
        String sql = "insert into session (name, start_at, end_at, price, limit_count, progress_status, recruitment_status, created_at)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"});
            ps.setString(1, session.getName());
            ps.setDate(2, Date.valueOf(session.getDate().getStartAt()));
            ps.setDate(3, Date.valueOf(session.getDate().getEndAt()));
            ps.setDouble(4, session.getCharge().getPrice());
            ps.setInt(5, session.getCharge().getLimitCount());
            ps.setString(6, session.getStatus().getProgressStatus().name());
            ps.setString(7, session.getStatus().getRecruitmentStatus().name());
            ps.setTimestamp(8, Timestamp.valueOf(session.getCreatedAt()));
            return ps;
        }, keyHolder);
        Long sessionId = (Long) keyHolder.getKey();
        saveImages(sessionId, session.getImages());
        return sessionId;
    }

    private void saveImages(Long sessionId, SessionImages images) {
        String sql = "insert into session_image (image_size, image_width, image_height, image_type, session_id) " +
                " values (?, ?, ?, ?, ?)";
        System.out.println(images.getImages());
        jdbcTemplate.batchUpdate(sql, images.getImages(), images.size(), (PreparedStatement ps, SessionImage image) -> {
            ps.setInt(1, image.getSize());
            ps.setDouble(2, image.getWidth());
            ps.setDouble(3, image.getHeight());
            ps.setString(4, image.getType().toString());
            ps.setLong(5, sessionId);
        });
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, name, start_at, end_at, price, limit_count, progress_status, recruitment_status, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = ((rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                new Period(toLocalDate(rs.getTimestamp(3)), toLocalDate(rs.getTimestamp(4))),
                imagesFindBySessionId(id),
                new SessionCharge(rs.getInt(5) > 0 ? true : false, rs.getLong(5), rs.getInt(6)),
                new SessionStatus(SessionProgressStatus.valueOf(rs.getString(7)), SessionRecruitmentStatus.valueOf(rs.getString(8))),
                studentsFindBySessionId(id),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10))
        ));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private SessionImages imagesFindBySessionId(Long id) {
        String sql = "select id, image_size, image_width, image_height, image_type from session_image where session_id = ?";
        RowMapper<SessionImage> rowMapper = ((rs, rowNum) -> new SessionImage(
                rs.getLong(1),
                rs.getInt(2),
                rs.getDouble(3),
                rs.getDouble(4),
                ImageType.from(rs.getString(5))
        ));
        List<SessionImage> images = jdbcTemplate.query(sql, new String[]{String.valueOf(id)}, rowMapper);
        System.out.println(images);

        return new SessionImages(images);
    }

    private SessionStudents studentsFindBySessionId(Long id) {
        String sql = "select ss.id, ss.user_id, ss.registration_at, nu.user_id, nu.password, nu.name, nu.email, nu.created_at, nu.updated_at " +
                "from session_student ss join ns_user nu on ss.user_id = nu.id " +
                "where session_id = ?";

        RowMapper<SessionStudent> rowMapper = ((rs, rowNum) -> new SessionStudent(
                rs.getLong(1),
                new NsUser(
                        rs.getLong(2),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        toLocalDateTime(rs.getTimestamp(8)),
                        toLocalDateTime(rs.getTimestamp(9))
                ),
                toLocalDateTime(rs.getTimestamp(3))
        ));
        List<SessionStudent> students = jdbcTemplate.query(sql, new String[]{String.valueOf(id)}, rowMapper);
        if (students.isEmpty()) {
            return new SessionStudents();
        }
        return new SessionStudents(students);
    }

    @Override
    public Long enroll(Session session, SessionStudent student) {
        String sql = "insert into session_student (user_id, registration_at, session_id) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"});
            ps.setLong(1, student.getUser().getId());
            ps.setTimestamp(2, Timestamp.valueOf(student.getRegistrationAt()));
            ps.setLong(3, session.getId());
            return ps;
        }, keyHolder);
        return (long) keyHolder.getKey();
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
