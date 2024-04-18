package nextstep.courses.infrastructure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.Sessions;
import nextstep.courses.domain.session.EnrollmentCount;
import nextstep.courses.domain.session.Name;
import nextstep.courses.domain.session.Schedule;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.image.CoverImage;
import nextstep.courses.domain.session.image.Dimensions;
import nextstep.courses.domain.session.image.Height;
import nextstep.courses.domain.session.image.ImageType;
import nextstep.courses.domain.session.image.Size;
import nextstep.courses.domain.session.image.Width;
import nextstep.courses.domain.session.strategy.StrategyType;

@Repository("SessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(final JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(final Session session) {
        final String sql = "insert into session "
                + "(name, status, start_date, end_date, strategy, fee, enrollment_limit, enrollment_count) "
                + "values(?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                session.name(),
                session.statusName(),
                session.startDate(),
                session.endDate(),
                session.strategyName(),
                session.fee(),
                session.enrollmentLimit(),
                session.currentEnrollmentCount()
        );
    }

    @Override
    public Sessions findAllByCourseId(final Long courseId) {
        final String courseSql = "select id, title, creator_id, created_at, updated_at from course where id = ?";
        final Course course = Optional.ofNullable(jdbcTemplate.queryForObject(courseSql, courseMapper(), courseId))
                .orElseThrow(NoSuchElementException::new);

        final String sessionSql = "select "
                + "s.id as session_id, "
                + "s.name as session_name, "
                + "s.status as session_status_name, "
                + "s.start_date as session_start_date, "
                + "s.end_date as session_end_date, "
                + "s.strategy as session_strategy_name, "
                + "s.fee as session_fee, "
                + "s.enrollment_limit as session_enrollment_limit, "
                + "s.enrollment_count as session_enrollment_count, "
                + "ci.id as cover_image_id, "
                + "ci.type as cover_image_type_name, "
                + "ci.size as cover_image_size, "
                + "ci.width as cover_image_width, "
                + "ci.height as cover_image_height "
                + "from session s "
                + "left join cover_image ci on s.id = ci.session_id"
                + "where course_id = ?";

        return new Sessions(jdbcTemplate.query(sessionSql, sessionMapper(course), courseId));
    }

    private RowMapper<Course> courseMapper() {
        return (resultSet, rowNumber) -> new Course(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getLong("creator_id"),
                toLocalDateTime(resultSet.getTimestamp("created_at")),
                toLocalDateTime(resultSet.getTimestamp("updated_at"))
        );
    }

    private LocalDateTime toLocalDateTime(final Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        return timestamp.toLocalDateTime();
    }

    private RowMapper<Session> sessionMapper(final Course course) {
        return (resultSet, rowNumber) -> {
            final Session session = new Session(
                    resultSet.getLong("session_id"),
                    new Name(resultSet.getString("session_name")),
                    SessionStatus.from(resultSet.getString("session_status_name")),
                    new Schedule(
                            toLocalDate(resultSet.getDate("session_start_date")),
                            toLocalDate(resultSet.getDate("session_end_date"))
                    ),
                    StrategyType.buildStrategy(
                            resultSet.getString("session_strategy_name"),
                            resultSet.getInt("session_fee"),
                            resultSet.getInt("session_enrollment_limit")
                    ),
                    new EnrollmentCount(resultSet.getInt("session_enrollment_count"))
            );

            session.assignCourse(course);
            assignCoverImageIfNotNull(session, resultSet);

            return session;
        };
    }

    private LocalDate toLocalDate(final Date date) {
        if (date == null) {
            return null;
        }

        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private void assignCoverImageIfNotNull(final Session session, final ResultSet resultSet) throws SQLException {
        if (resultSet.getObject("cover_image_id") == null) {
            return;
        }

        final CoverImage coverImage = new CoverImage(
                resultSet.getLong("cover_image_id"),
                ImageType.from(resultSet.getString("cover_image_type_name")),
                new Size(resultSet.getLong("cover_image_size")),
                new Dimensions(
                        new Width(resultSet.getInt("cover_image_width")),
                        new Height(resultSet.getInt("cover_image_height"))
                )
        );

        session.assignCoverImage(coverImage);
    }
}
