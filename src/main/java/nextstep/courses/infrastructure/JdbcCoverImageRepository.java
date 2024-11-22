package nextstep.courses.infrastructure;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.cover.CoverImageRepository;
import nextstep.courses.domain.cover.ImageDimension;
import nextstep.courses.domain.cover.ImageSize;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CoverImage> findBySessionId(Long sessionId) {
        String sql = "SELECT file_name, image_size, extension, width, height FROM cover_image WHERE session_id = ?";

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private final RowMapper<CoverImage> rowMapper = (rs, rowNum) -> CoverImage.of(
            rs.getString(1),
            ImageSize.of(rs.getInt(2)),
            rs.getString(3),
            ImageDimension.of(rs.getInt(4), rs.getInt(5))
    );

    @Override
    public void save(List<CoverImage> coverImages, Long sessionId) {
        String sql = "INSERT INTO cover_image (session_id, file_name, image_size, extension, width, height) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CoverImage coverImage = coverImages.get(i);
                ps.setLong(1, sessionId);
                ps.setString(2, coverImage.getFileName());
                ps.setInt(3, coverImage.getImageSize());
                ps.setString(4, coverImage.getExtension().name());
                ps.setInt(5, coverImage.getWidth());
                ps.setInt(6, coverImage.getHeight());
            }

            @Override
            public int getBatchSize() {
                return coverImages.size();
            }
        });

    }
}