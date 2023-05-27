package nextstep.courses.infrastructure.persistence.dao;

import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.infrastructure.persistence.entity.ImageEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("imageEntityRepository")
public class ImageEntityRepository {

  private final JdbcTemplate jdbcTemplate;

  public ImageEntityRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Optional<ImageEntity> findById(Long coverImageId) {
    String sql = "select id, original_file_name, image_type, cover_img_url, created_at, updated_at from image where id = ?";
    try {
      return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper(), coverImageId));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  private RowMapper<ImageEntity> rowMapper() {
    return (rs, rowNum) -> {
      Long id = rs.getLong("id");
      String originalFileName = rs.getString("original_file_name");
      String imageType = rs.getString("image_type");
      String coverImgUrl = rs.getString("cover_img_url");
      LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
      LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();

      return new ImageEntity(id, originalFileName, imageType, coverImgUrl, createdAt, updatedAt);
    };
  }
}
