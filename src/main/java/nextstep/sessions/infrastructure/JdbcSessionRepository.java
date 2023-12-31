package nextstep.sessions.infrastructure;

import nextstep.common.Period;
import nextstep.sessions.domain.ImageSize;
import nextstep.sessions.domain.ImageType;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionApprovalStatus;
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
        String sql = "insert into session (name, start_at, end_at, price, limit_count, progress_status, recruitment_status, created_at, user_id)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            ps.setLong(9, session.getInstructor().getId());
            return ps;
        }, keyHolder);
        Long sessionId = (Long) keyHolder.getKey();
        saveImages(sessionId, session.getImages());
        saveStudents(sessionId, session.getStudents());
        return sessionId;
    }

    private void saveImages(Long sessionId, SessionImages images) {
        String sql = "insert into session_image (image_size, image_width, image_height, image_type, session_id) " +
                " values (?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, images.getImages(), images.size(), (PreparedStatement ps, SessionImage image) -> {
            ps.setInt(1, image.getImageSize().getSize());
            ps.setDouble(2, image.getImageSize().getWidth());
            ps.setDouble(3, image.getImageSize().getHeight());
            ps.setString(4, image.getType().toString());
            ps.setLong(5, sessionId);
        });
    }

    private void saveStudents(Long sessionId, SessionStudents students) {
        String sql = "insert into session_student (user_id, registration_at, approval_status, session_id)" +
                " values (?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, students.getStudents(), students.getStudents().size(), (PreparedStatement ps, SessionStudent student) -> {
            ps.setLong(1, student.getUser().getId());
            ps.setTimestamp(2, Timestamp.valueOf(student.getRegistrationAt()));
            ps.setString(3, student.getStatus().name());
            ps.setLong(4, sessionId);
        });
    }

    @Override
    public Session findById(Long id) {
        String sql = "select s.id, s.name, s.start_at, s.end_at, s.price, s.limit_count, s.progress_status, s.recruitment_status, s.created_at, s.updated_at, nu.id, nu.user_id, nu.password, nu.name, nu.email, nu.created_at, nu.updated_at " +
                " from session s join ns_user nu on s.user_id = nu.id " +
                " where s.id = ?";
        RowMapper<Session> rowMapper = ((rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                new Period(toLocalDate(rs.getTimestamp(3)), toLocalDate(rs.getTimestamp(4))),
                imagesFindBySessionId(id),
                new SessionCharge(rs.getInt(5) > 0 ? true : false, rs.getLong(5), rs.getInt(6)),
                new SessionStatus(SessionProgressStatus.valueOf(rs.getString(7)), SessionRecruitmentStatus.valueOf(rs.getString(8))),
                studentsFindBySessionId(id),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10)),
                new NsUser(
                        rs.getLong(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        toLocalDateTime(rs.getTimestamp(16)),
                        toLocalDateTime(rs.getTimestamp(17))
                )
        ));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private SessionImages imagesFindBySessionId(Long id) {
        String sql = "select id, image_size, image_width, image_height, image_type from session_image where session_id = ?";
        RowMapper<SessionImage> rowMapper = ((rs, rowNum) -> new SessionImage(
                rs.getLong(1),
                new ImageSize(
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getDouble(4)),
                ImageType.from(rs.getString(5))
        ));
        List<SessionImage> images = jdbcTemplate.query(sql, new String[]{String.valueOf(id)}, rowMapper);
        System.out.println(images);

        return new SessionImages(images);
    }

    private SessionStudents studentsFindBySessionId(Long id) {
        String sql = "select ss.id, ss.registration_at, ss.approval_status, ss.session_id, nu.id, nu.user_id, nu.password, nu.name, nu.email, nu.created_at, nu.updated_at " +
                "from session_student ss join ns_user nu on ss.user_id = nu.id " +
                "where session_id = ?";

        RowMapper<SessionStudent> rowMapper = ((rs, rowNum) -> new SessionStudent(
                rs.getLong(1),
                new NsUser(
                        rs.getLong(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        toLocalDateTime(rs.getTimestamp(10)),
                        toLocalDateTime(rs.getTimestamp(11))
                ),
                toLocalDateTime(rs.getTimestamp(2)),
                SessionApprovalStatus.valueOf(rs.getString(3)),
                rs.getLong(4)
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

    @Override
    public SessionStudent studentFindBySessionIdAndUserId(Long sessionId, Long userId) {
        String sql = "select ss.id, ss.registration_at, ss.approval_status, ss.session_id, nu.user_id, nu.password, nu.name, nu.email, nu.created_at, nu.updated_at " +
                " from session_student ss join ns_user nu on ss.user_id = nu.id " +
                " where ss.session_id = ? and ss.user_id = ?";
        RowMapper<SessionStudent> rowMapper = ((rs, rowNum) -> new SessionStudent(
                rs.getLong(1),
                new NsUser(
                        userId,
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        toLocalDateTime(rs.getTimestamp(9)),
                        toLocalDateTime(rs.getTimestamp(10))
                ),
                toLocalDateTime(rs.getTimestamp(2)),
                SessionApprovalStatus.valueOf(rs.getString(3)),
                rs.getLong(4)
        ));
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId, userId);
    }

    @Override
    public void approvalStudent(SessionStudent student) {
        String sql = "update session_student set approval_status = 'APPROVAL' where session_id = ? and user_id = ?";
        jdbcTemplate.update(sql, student.getSessionId(), student.getUser().getId());
    }

    @Override
    public void cancelStudent(SessionStudent student) {
        String sql = "update session_student set approval_status = 'CANCEL' where session_id = ? and user_id = ?";
        jdbcTemplate.update(sql, student.getSessionId(), student.getUser().getId());
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
