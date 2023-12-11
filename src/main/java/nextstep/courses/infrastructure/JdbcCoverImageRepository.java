package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.courses.domain.coverimage.CoverImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {

  private JdbcOperations jdbcTemplate;

  public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int save(CoverImage coverImage) {
    String sql = "insert into cover_image (name, cover_image_type, image_file_size, width, height,created_at) values(?, ?, ?, ?, ?, ?)";
    return jdbcTemplate.update(sql, coverImage.getName(), coverImage.getCoverImageType().getName(),
        coverImage.getImageFileSize().getSize(), coverImage.getImageSize().getWidth(),
        coverImage.getImageSize().getHeight(),coverImage.getCreatedAt());
  }

  @Override
  public CoverImage findById(Long id) {
    String sql = "select id, name, cover_image_type, image_file_size, width, height, created_at, updated_at from cover_image where id = ?";
    RowMapper<CoverImage> rowMapper = (rs, rowNum) -> CoverImage.defaultOf(
        rs.getLong(1),
        rs.getString(2),
        rs.getString(3),
        rs.getLong(4),
        rs.getLong(5),
        rs.getLong(6),
        toLocalDateTime(rs.getTimestamp(7)),
        toLocalDateTime(rs.getTimestamp(8))
        );
    return jdbcTemplate.queryForObject(sql, rowMapper, id);
  }

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }
}
