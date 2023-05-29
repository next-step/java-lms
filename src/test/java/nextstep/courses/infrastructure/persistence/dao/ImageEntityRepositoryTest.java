package nextstep.courses.infrastructure.persistence.dao;

import static nextstep.courses.domain.ImageType.JPEG;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import nextstep.courses.domain.Url;
import nextstep.courses.infrastructure.persistence.entity.ImageEntity;
import nextstep.users.infrastructure.repository.UserRepositoryImplTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class ImageEntityRepositoryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImplTest.class);
  private final ImageEntityRepository imageEntityRepository;

  public ImageEntityRepositoryTest(@Autowired JdbcTemplate jdbcTemplate) {
    this.imageEntityRepository = new ImageEntityRepository(jdbcTemplate);
  }

  /**
   * VALUES (100, 'original_file_name1', 'JPEG', 'cover_img_url1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
   */
  @Test
  void 임시_데이터에서_100L_이미지_찾고_검증() {
    Optional<ImageEntity> imageEntity = imageEntityRepository.findById(100L);
    assertThat(imageEntity).isNotNull();

    imageEntity.ifPresent(entity -> {
      assertThat(entity.getId()).isEqualTo(100L);
      assertThat(entity.getOriginalFileName()).isEqualTo("original_file_name1");
      assertThat(entity.getImageType()).isEqualTo(JPEG);
      assertThat(entity.getCoverImgUrl()).isEqualTo(new Url("cover_img_url1"));
    });
  }

  @Test
  void 임시_데이터에_존재하지_않는_101L_이미지_조회_실패() {
    Optional<ImageEntity> optionalImageEntity = imageEntityRepository.findById(101L);
    assertThat(optionalImageEntity).isEmpty();
  }
}