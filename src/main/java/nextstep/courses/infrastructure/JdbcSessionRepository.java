package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.Image.Dimension;
import nextstep.courses.domain.ImageType;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionDuration;
import nextstep.courses.domain.SessionPayType;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionState;
import nextstep.courses.domain.SessionStudent;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session save(Session session) {
        Long imageId = insertImage(session.getImage());
        Long sessionId = insertSession(session, imageId);
        session.setId(sessionId);
        return session;
    }

    private Long insertImage(Image image) {
        String imageSql = "insert into image (size, type, width, height, created_at) values(?, ?, ?, ?, ?)";
        KeyHolder imageKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(imageSql, new String[]{"id"});
            ps.setInt(1, image.getSize());
            ps.setString(2, image.getType());
            ps.setInt(3, image.getWidth());
            ps.setInt(4, image.getHeight());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        }, imageKeyHolder);
        return imageKeyHolder.getKey().longValue();
    }

    private Long insertSession(Session session, Long imageId) {
        String sql = "insert into session (course_id, image_id, start_date, end_date, pay_type, state, fee, created_at) values(?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, session.getCourseId());
            ps.setLong(2, imageId);
            ps.setTimestamp(3, Timestamp.valueOf(session.getStartDate().atStartOfDay()));
            ps.setTimestamp(4, Timestamp.valueOf(session.getEndDate().atStartOfDay()));
            ps.setString(5, session.getPayType());
            ps.setString(6, session.getState());
            ps.setLong(7, session.getFee());
            ps.setTimestamp(8, Timestamp.valueOf(session.getCreatedAt()));
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Session findById(Long id) {
        String sql = "select s.id, s.course_id, s.image_id, s.start_date, s.end_date, s.pay_type, s.state, s.max_student, s.fee, c.title, c.creator_id, i.size, i.type, i.width, i.height "
            + "from session s "
            + "join course c on s.course_id = c.id "
            + "join image i on s.image_id = i.id "
            + "where s.id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                new Course(rs.getLong(2), rs.getString(10), rs.getLong(11)),
                new SessionDuration(toLocalDate(rs.getTimestamp(4)), toLocalDate(rs.getTimestamp(5))),
                new Image(rs.getInt(12), ImageType.valueOf(rs.getString(13)),new Dimension(rs.getInt(14), rs.getInt(15))),
                SessionPayType.valueOf(rs.getString(6)),
                SessionState.valueOf(rs.getString(7)),
                rs.getInt(8),
                rs.getLong(9),
            new SessionStudent( rs.getInt(8))
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public void saveStudents(Session session) {
        String sql = "insert into study_student (session_id, student_id, created_at) values(?, ?, ?)";
        session.getStudentsId().forEach(studentId -> jdbcTemplate.update(sql, session.getId(), studentId, Timestamp.valueOf(LocalDateTime.now())));
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }
}
