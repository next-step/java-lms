package nextstep.courses.infrastructure.persistence.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.infrastructure.persistence.entity.ImageEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("imageEntityRepository")
public class ImageEntityRepository {

  private static final String ID = "id";
  private static final String ORIGINAL_FILE_NAME = "original_file_name";
  private static final String IMAGE_TYPE = "image_type";
  private static final String COVER_IMG_URL = "cover_img_url";
  private static final String CREATED_AT = "created_at";
  private static final String UPDATED_AT = "updated_at";

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
      Long id = rs.getLong(ID);
      String originalFileName = rs.getString(ORIGINAL_FILE_NAME);
      String imageType = rs.getString(IMAGE_TYPE);
      String coverImgUrl = rs.getString(COVER_IMG_URL);
      LocalDateTime createdAt = toLocalDateTime(rs.getTimestamp(CREATED_AT));
      LocalDateTime updatedAt = toLocalDateTime(rs.getTimestamp(UPDATED_AT));

      return new ImageEntity(id, originalFileName, imageType, coverImgUrl, createdAt, updatedAt);
    };
  }

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }
}
