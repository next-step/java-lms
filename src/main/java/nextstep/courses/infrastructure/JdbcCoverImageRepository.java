package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.CoverImageRepository;
import nextstep.courses.domain.Session;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImage coverImage) {
        String sql = "INSERT INTO cover_image (capacity, type, width, height, session_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                coverImage.getCapacity(),
                coverImage.getType(),
                coverImage.getWidth(),
                coverImage.getHeight(),
                coverImage.getSession().getId(),
                coverImage.getCreatedAt(),
                coverImage.getUpdatedAt()
        );
    }

    @Override
    public Optional<CoverImage> findById(Long coverImageId) {
        String sql = "SELECT id, capacity, type, width, height, session_id, created_at, updated_at FROM cover_image WHERE id = ?";

        AtomicReference<Long> sessionId = new AtomicReference<>();
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> {
            sessionId.set(rs.getLong(6));
            return new CoverImage(
                    LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp(7)),
                    LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp(8)),
                    rs.getLong(2),
                    rs.getString(3),
                    rs.getLong(4),
                    rs.getLong(5),
                    null,
                    rs.getLong(1)
            );
        };
        Optional<CoverImage> coverImage = Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, coverImageId));

        if (coverImage.isPresent() && sessionId.get() != null) {
            String sessionSql = "SELECT * from session WHERE id = ?";
            RowMapper<Session> sessionRowMapper = (rs, rowNum) ->
                    new Session(
                            LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp("created_at")),
                            LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp("updated_at")),
                            null,
                            rs.getLong("fee"),
                            rs.getInt("count"),
                            rs.getString("status"),
                            LocalDateMappingUtil.toLocalDate(rs.getTimestamp("start_date")),
                            LocalDateMappingUtil.toLocalDate(rs.getTimestamp("end_date")),
                            rs.getLong("course_id"),
                            rs.getLong("id")
                    );
            Session session = jdbcTemplate.queryForObject(sessionSql, sessionRowMapper, sessionId.get());
            coverImage.get().changeSession(session);
        }

        return coverImage;
    }
}
