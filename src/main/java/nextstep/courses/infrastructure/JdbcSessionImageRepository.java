package nextstep.courses.infrastructure;

import nextstep.courses.InvalidImageFormatException;
import nextstep.courses.domain.SystemTimeStamp;
import nextstep.courses.domain.image.ImageFormat;
import nextstep.courses.domain.image.SessionImageRepository;
import nextstep.courses.domain.image.ImageType;
import nextstep.courses.domain.image.SessionImage;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("SessionImageRepository")
public class JdbcSessionImageRepository implements SessionImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionImage image) {
        String sql = "insert into image (name, session_id, image_size, width, height, image_type, created_at, updated_at) values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql
                , image.getName()
                , image.getSessionId()
                , image.getImageFormat().getImageSize()
                , image.getImageFormat().getWidth()
                , image.getImageFormat().getHeight()
                , image.getImageFormat().getImageType()
                , image.getCreatedAt()
                , image.getUpdatedAt());
    }

    @Override
    public Optional<SessionImage> findBySessionId(Long sessionId) {
        String sql = "select id, " +
                "       name, " +
                "       session_id, " +
                "       image_size, " +
                "       width, " +
                "       height, " +
                "       image_type, " +
                "       created_at, " +
                "       updated_at " +
                "       from image " +
                "       where session_id = ?";

        RowMapper<SessionImage> rowMapper = (rs, rowNum) -> {
            try {
                return new SessionImage(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3),
                        new ImageFormat(rs.getInt(4),
                                rs.getInt(5),
                                rs.getInt(6),
                                ImageType.valueOf(rs.getString(7))),
                        new SystemTimeStamp(toLocalDateTime(rs.getTimestamp(8)), toLocalDateTime(rs.getTimestamp(9))));
            } catch (InvalidImageFormatException e) {
                throw new RuntimeException(e);
            }
        };

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, sessionId));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
