package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.info.basic.SessionThumbnail;
import nextstep.courses.dto.ImageDto;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SessionThumbnail findThumbnailBySessionId(Long sessionId) {
        List<ImageDto> imageDtos = findBySessionId(sessionId);
        if (imageDtos.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 이미지입니다.");
        }

        SessionThumbnail thumbnail = new SessionThumbnail();
        for (ImageDto imageDto : imageDtos) {
            thumbnail.addThumbnail(imageDto.getFileName(), imageDto.getFileSize(),
                    imageDto.getWidth(), imageDto.getHeight());
        }
        return thumbnail;
    }

    private List<ImageDto> findBySessionId(Long sessionId) {
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
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
