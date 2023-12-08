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

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImage coverImage) {
        String sql = "insert into cover_image (name, extension, file_volume, width, height, created_at) values(?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                coverImage.getName(),
                coverImage.getExtension(),
                coverImage.getFileVolume(),
                coverImage.getWidth(),
                coverImage.getHeight(),
                coverImage.getCreatedAt());
    }

    @Override
    public CoverImage findById(Long id) {
        String sql = "select id, name, extension, file_volume, width, height, created_at, updated_at from cover_image where id = ?";

        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                new FileNameStructure(rs.getString(2), rs.getString(3)),
                new FileMetadata(rs.getLong(4), new FileSize(rs.getInt(5), rs.getInt(6))),
                toLocalDateTime(rs.getTimestamp(7)),
                toLocalDateTime(rs.getTimestamp(8)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
