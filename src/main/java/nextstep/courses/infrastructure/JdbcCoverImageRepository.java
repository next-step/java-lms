package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.CoverImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
                coverImage.getSessionId(),
                coverImage.getCreatedAt(),
                coverImage.getUpdatedAt()
        );
    }

    @Override
    public Optional<CoverImage> findById(Long coverImageId) {
        String sql = "SELECT id, capacity, type, width, height, session_id, created_at, updated_at FROM cover_image WHERE id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp(7)),
                LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp(8)),
                rs.getLong(2),
                rs.getString(3),
                rs.getLong(4),
                rs.getLong(5),
                rs.getLong(6),
                rs.getLong(1)
        );
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, coverImageId));
    }
}
