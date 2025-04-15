package nextstep.courses.infrastructure;

import nextstep.courses.dto.ImageDto;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ImageDto findBySessionId(Long sessionId) {
        String sql = "SELECT id, session_id, file_name, file_size, width, height FROM image WHERE session_id = ?";
        RowMapper<ImageDto> rowMapper = (rs, rowNum) -> ImageDto.builder()
                .id(rs.getLong("id"))
                .sessionId(rs.getLong("session_id"))
                .fileName(rs.getString("file_name"))
                .fileSize(rs.getLong("file_size"))
                .width(rs.getInt("width"))
                .height(rs.getInt("height"))
                .createdAt(toLocalDateTime(rs.getTimestamp("created_at")))
                .updatedAt(toLocalDateTime(rs.getTimestamp("updated_at")))
                .build();
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
