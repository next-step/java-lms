package nextstep.courses.infrastructure;

import static nextstep.courses.domain.CourseBuilder.aCourse;
import static nextstep.courses.domain.CoverImageBuilder.aCoverImage;
import static nextstep.courses.domain.SessionBuilder.aSession;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import nextstep.courses.domain.Duration;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionPaymentType;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.Sessions;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(Session session) {
        String sql = "INSERT INTO session ("
                + "course_id, session_type, price, session_limit, "
                + "cover_image_name, cover_image_extension, "
                + "cover_byte_size, cover_image_width, cover_image_height, "
                + "status, start_date, end_date, created_at, updated_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                session.getCourse().getId(),
                session.getSessionPayment().getTypeString(),
                session.getSessionPayment().getAmount(),
                session.getEnrollment().getLimits(),
                session.getCoverImage().getName(),
                session.getCoverImage().getExtension().name(),
                session.getCoverImage().getByteSize(),
                session.getCoverImage().getWidth(),
                session.getCoverImage().getHeight(),
                session.getSessionStatus().name(),
                session.getDuration().getStartDate(),
                session.getDuration().getEndDate(),
                session.getCreatedAt(),
                session.getUpdatedAt()
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, session_type, price, session_limit, "
                + "cover_image_name, cover_byte_size, cover_image_width, cover_image_height, "
                + "status, start_date, end_date, created_at, updated_at from session where id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                        aSession()
                                .withId(rs.getLong("id"))
                                .withCourse(aCourse().withId(rs.getLong("course_id")).build())
                                .withAmountOfPrice(rs.getLong("price"))
                                .withSessionPaymentType(SessionPaymentType.from(rs.getString("session_type")))
                                .withLimitOfUsers(rs.getInt("session_limit"))
                                .withDuration(new Duration(rs.getTimestamp("start_date").toLocalDateTime(),
                                        rs.getTimestamp("end_date").toLocalDateTime()))
                                .withCoverImage(
                                        aCoverImage()
                                                .withName(rs.getString("cover_image_name"))
                                                .withByteSize(rs.getLong("cover_byte_size"))
                                                .withWidth(rs.getDouble("cover_image_width"))
                                                .withHeight(rs.getDouble("cover_image_height"))
                                                .build()
                                )
                                .withSessionStatus(SessionStatus.valueOf(rs.getString("status")))
                                .withCreatedAt(toLocalDateTime(rs.getTimestamp("created_at")))
                                .withUpdatedAt(toLocalDateTime(rs.getTimestamp("updated_at")))
                                .build()
                , id);
    }

    @Override
    public Optional<Sessions> findByCourseId(Long courseId) {
        String sql = "select id, course_id, session_type, price, session_limit, "
                + "cover_image_name, cover_byte_size, cover_image_width, cover_image_height, "
                + "status, start_date, end_date, created_at, updated_at "
                + "from session "
                + "where course_id = ?";
        List<Session> sessionList = jdbcTemplate.query(sql, (rs, rowNum) ->
                        aSession()
                                .withId(rs.getLong("id"))
                                .withCourse(aCourse().withId(rs.getLong("course_id")).build())
                                .withAmountOfPrice(rs.getLong("price"))
                                .withSessionPaymentType(SessionPaymentType.from(rs.getString("session_type")))
                                .withLimitOfUsers(rs.getInt("session_limit"))
                                .withDuration(new Duration(rs.getTimestamp("start_date").toLocalDateTime(),
                                        rs.getTimestamp("end_date").toLocalDateTime()))
                                .withCoverImage(
                                        aCoverImage()
                                                .withName(rs.getString("cover_image_name"))
                                                .withByteSize(rs.getLong("cover_byte_size"))
                                                .withWidth(rs.getDouble("cover_image_width"))
                                                .withHeight(rs.getDouble("cover_image_height"))
                                                .build()
                                )
                                .withSessionStatus(SessionStatus.valueOf(rs.getString("status")))
                                .withCreatedAt(toLocalDateTime(rs.getTimestamp("created_at")))
                                .withUpdatedAt(toLocalDateTime(rs.getTimestamp("updated_at")))
                                .build()
                , courseId
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
