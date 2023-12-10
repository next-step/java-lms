package nextstep.lms.infrastructure;

import nextstep.lms.domain.CoverImage;
import nextstep.lms.domain.FileMetadata;
import nextstep.lms.domain.FileNameStructure;
import nextstep.lms.domain.FileSize;
import nextstep.lms.repository.CoverImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImage coverImage) {
        String sql = "insert into cover_image (session_id, name, extension, file_volume, width, height, created_at) values(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                coverImage.getSessionId(),
                coverImage.getName(),
                coverImage.getExtension(),
                coverImage.getFileVolume(),
                coverImage.getWidth(),
                coverImage.getHeight(),
                coverImage.getCreatedAt());
    }

    @Override
    public List<CoverImage> findBySessionId(Long sessionId) {
        String sql = "select id, session_id, name, extension, file_volume, width, height, created_at, updated_at from cover_image where session_id = ?";

        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                rs.getLong(2),
                new FileNameStructure(rs.getString(3), rs.getString(4)),
                new FileMetadata(rs.getLong(5), new FileSize(rs.getInt(6), rs.getInt(7))),
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9)));

        List<CoverImage> coverImages = jdbcTemplate.query(sql, rowMapper, sessionId);
        return coverImages;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
