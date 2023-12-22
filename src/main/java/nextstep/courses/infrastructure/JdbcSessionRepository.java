package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.Duration;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionPaymentType;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.Sessions;
import nextstep.courses.dto.SessionDTO;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(SessionDTO session) {
        String sql = "INSERT INTO session ("
                + "course_id, session_type, price, session_limit, "
                + "cover_image_name, cover_image_extension, "
                + "cover_byte_size, cover_image_width, cover_image_height, "
                + "status, start_date, end_date, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                session.getCourseId(),
                session.getSessionPaymentDTO().getTypeString(),
                session.getSessionPaymentDTO().getAmount(),
                session.getEnrollmentDTO().getLimits().getLimit(),
                session.getCoverImageDTO().getName(),
                session.getCoverImageDTO().getExtension().name(),
                session.getCoverImageDTO().getByteSize(),
                session.getCoverImageDTO().getWidth(),
                session.getCoverImageDTO().getHeight(),
                session.getSessionStatus().name(),
                session.getDurationDTO().getStartDate(),
                session.getDurationDTO().getEndDate(),
                session.getCreatedAt()
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, session_type, price, session_limit, "
                + "cover_image_name, cover_byte_size, cover_image_width, cover_image_height, "
                + "status, start_date, end_date, created_at, updated_at from session where id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Session(rs.getLong("id"),
                        new Course(rs.getLong("course_id")),
                        rs.getLong("price"),
                        SessionPaymentType.from(rs.getString("session_type")),
                        rs.getInt("session_limit"),
                        new Duration(
                                rs.getTimestamp("start_date").toLocalDateTime(),
                                rs.getTimestamp("end_date").toLocalDateTime()
                        ),
                        SessionStatus.valueOf(rs.getString("status")),
                        new CoverImage(
                                rs.getString("cover_image_name"),
                                rs.getLong("cover_byte_size"),
                                rs.getDouble("cover_image_width"),
                                rs.getDouble("cover_image_height")
                        ),
                        toLocalDateTime(rs.getTimestamp("created_at")),
                        toLocalDateTime(rs.getTimestamp("updated_at"))
                ),id);
    }

    @Override
    public Optional<Sessions> findByCourseId(Long courseId) {
        String sql = "select id, course_id, session_type, price, session_limit, "
                + "cover_image_name, cover_byte_size, cover_image_width, cover_image_height, "
                + "status, start_date, end_date, created_at, updated_at "
                + "from session "
                + "where course_id = ?";
        List<Session> sessionList = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Session(
                        rs.getLong("id"),
                        new Course(courseId),
                        rs.getLong("price"),
                        SessionPaymentType.from(rs.getString("session_type")),
                        rs.getInt("session_limit"),
                        new Duration(rs.getTimestamp("start_date").toLocalDateTime(),
                                rs.getTimestamp("end_date").toLocalDateTime()),
                        SessionStatus.valueOf(rs.getString("status")),
                        new CoverImage(
                                rs.getString("cover_image_name"),
                                rs.getLong("cover_byte_size"),
                                rs.getDouble("cover_image_width"),
                                rs.getDouble("cover_image_height")),
                        toLocalDateTime(rs.getTimestamp("created_at")),
                        toLocalDateTime(rs.getTimestamp("updated_at"))

                )
                ,courseId
        );
        return Optional.of(new Sessions(new LinkedHashSet<>(sessionList)));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
