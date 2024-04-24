package nextstep.courses.infrastructure.session;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("SessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(final JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(final SessionEntity sessionEntity) {
        final String sql = "insert into session "
                + "(name, status, start_date, end_date, strategy, fee, enrollment_limit, enrollment_count, created_at, updated_at) "
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, sessionEntity.name());
            preparedStatement.setString(2, sessionEntity.status());
            preparedStatement.setDate(3, Date.valueOf(sessionEntity.startDate()));
            preparedStatement.setDate(4, Date.valueOf(sessionEntity.endDate()));
            preparedStatement.setString(5, sessionEntity.strategy());
            preparedStatement.setInt(6, sessionEntity.fee());
            preparedStatement.setInt(7, sessionEntity.enrollmentLimit());
            preparedStatement.setInt(8, sessionEntity.enrollmentCount());
            preparedStatement.setTimestamp(9, Timestamp.valueOf(sessionEntity.createdAt()));
            preparedStatement.setTimestamp(10, Timestamp.valueOf(sessionEntity.updatedAt()));

            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(final SessionEntity sessionEntity) {
        final String sql = "update session set "
                + "name = ?, "
                + "status = ?, "
                + "start_date = ?, "
                + "end_date = ?, "
                + "strategy = ?, "
                + "fee = ?, "
                + "enrollment_limit = ?, "
                + "enrollment_count = ?, "
                + "course_id = ?, "
                + "created_at = ?, "
                + "updated_at = ? "
                + "where id = ?";

        jdbcTemplate.update(
                sql,
                sessionEntity.name(),
                sessionEntity.status(),
                sessionEntity.startDate(),
                sessionEntity.endDate(),
                sessionEntity.strategy(),
                sessionEntity.fee(),
                sessionEntity.enrollmentLimit(),
                sessionEntity.enrollmentCount(),
                sessionEntity.courseId(),
                sessionEntity.createdAt(),
                sessionEntity.updatedAt(),
                sessionEntity.id()
        );
    }

    @Override
    public Optional<SessionEntity> findById(final Long id) {
        final String sql = "select "
                + "id, name, status, start_date, end_date, strategy, fee, enrollment_limit, enrollment_count, created_at, updated_at "
                + "from session "
                + "where id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, sessionEntityMapper(), id));
    }

    @Override
    public List<SessionEntity> findAllByCourseId(final Long courseId) {
        final String sql = "select "
                + "id, name, status, start_date, end_date, strategy, fee, enrollment_limit, enrollment_count, created_at, updated_at  "
                + "from session "
                + "where course_id = ?";

        return jdbcTemplate.query(sql, sessionEntityMapper(), courseId);
    }

    private RowMapper<SessionEntity> sessionEntityMapper() {
        return (resultSet, rowNumber) -> new SessionEntity(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("status"),
                resultSet.getDate("start_date").toLocalDate(),
                resultSet.getDate("end_date").toLocalDate(),
                resultSet.getString("strategy"),
                resultSet.getInt("fee"),
                resultSet.getInt("enrollment_limit"),
                resultSet.getInt("enrollment_count"),
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
}
