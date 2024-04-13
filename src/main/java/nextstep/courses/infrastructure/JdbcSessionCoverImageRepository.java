package nextstep.courses.infrastructure;

import nextstep.courses.domain.ImageType;
import nextstep.courses.domain.SessionCoverImage;
import nextstep.courses.domain.SessionCoverImageRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionCoverImageRepository")
public class JdbcSessionCoverImageRepository implements SessionCoverImageRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionCoverImage coverImage) {
        String sql = "insert into session_cover_images (id, session_id, size_of_bytes, width, height, image_type) " +
                "values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                coverImage.getId(),
                coverImage.getSessionId(),
                coverImage.getSizeOfBytes(),
                coverImage.getWidth(),
                coverImage.getHeight(),
                coverImage.getType().toString()
        );
    }

    @Override
    public SessionCoverImage findById(Long id) {
        try {
            String sql = "select id, session_id, size_of_bytes, width, height, image_type " +
                    "from session_cover_images where id = ?";

            RowMapper<SessionCoverImage> rowMapper = (rs, rowNum) -> {
                return new SessionCoverImage(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getInt(3),
                        Enum.valueOf(ImageType.class, rs.getString(6)),
                        rs.getInt(4),
                        rs.getInt(5));
            };
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
