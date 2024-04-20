package nextstep.courses.infrastructure.session.image;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("CoverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(final JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(final CoverImageEntity coverImageEntity) {
        final String sql = "insert into cover_image (type, size, width, height, session_id) values(?, ?, ?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, coverImageEntity.type());
            preparedStatement.setLong(2, coverImageEntity.size());
            preparedStatement.setInt(3, coverImageEntity.width());
            preparedStatement.setInt(4, coverImageEntity.height());
            preparedStatement.setLong(5, coverImageEntity.sessionId());

            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<CoverImageEntity> findById(final Long id) {
        final String sql = "select id, type, size, width, height, session_id from cover_image where id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, coverImageEntityMapper(), id));
    }

    @Override
    public Optional<CoverImageEntity> findBySessionId(final Long sessionId) {
        final String sql = "select id, type, size, width, height, session_id from cover_image where session_id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, coverImageEntityMapper(), sessionId));
    }

    private RowMapper<CoverImageEntity> coverImageEntityMapper() {
        return (resultSet, rowNumber) -> new CoverImageEntity(
                resultSet.getLong("id"),
                resultSet.getString("type"),
                resultSet.getLong("size"),
                resultSet.getInt("width"),
                resultSet.getInt("height"),
                resultSet.getLong("session_id")
        );
    }
}
