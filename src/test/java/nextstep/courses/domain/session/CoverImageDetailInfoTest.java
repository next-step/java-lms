package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.CoverImageDetailInfo.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImageDetailInfoTest {

  @Test
  @DisplayName("정상 이미지 사이즈, width, height 를 입력한 경우" +
      "CoverImageDetailInfo 생성 테스트")
  void coverImageDetailImageTest() {
    CoverImageDetailInfo coverImageDetailInfo = new CoverImageDetailInfo(ONE_MB, MIN_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_HEIGHT);
    assertThat(coverImageDetailInfo.getImageSize()).isEqualTo(ONE_MB);
    assertThat(coverImageDetailInfo.getImageWidth()).isEqualTo(MIN_COVER_IMAGE_WIDTH);
    assertThat(coverImageDetailInfo.getImageHeight()).isEqualTo(MIN_COVER_IMAGE_HEIGHT);
  }

  @Test
  @DisplayName("이미지 사이즈 최대 사이즈를 넘긴 경우" +
      "exception 테스트")
  void coverImageDetailInfoTest2() {
    int given = ONE_MB * 100;
    assertThatThrownBy(() -> new CoverImageDetailInfo(ONE_MB * 100, MIN_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_HEIGHT))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(EXCEED_MAX_COVER_IMAGE_SIZE, given));
  }

  @Test
  @DisplayName("이미지 width 최소 사이즈를 못 넘긴 경우" +
      "exception 테스트")
  void coverImageDetailInfoTest3() {
    assertThatThrownBy(() -> new CoverImageDetailInfo(ONE_MB, MIN_COVER_IMAGE_WIDTH - 10, MIN_COVER_IMAGE_HEIGHT))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(INVALID_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_WIDTH - 10));
  }

  @Test
  @DisplayName("이미지 height 최소 사이즈를 못 넘긴 경우" +
      "exception 테스트")
  void coverImageDetailInfoTest4() {
    assertThatThrownBy(() -> new CoverImageDetailInfo(ONE_MB, MIN_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_HEIGHT - 10))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(INVALID_COVER_IMAGE_HEIGHT, MIN_COVER_IMAGE_HEIGHT - 10));
  }

  @Test
  @DisplayName("width와 height의 비율이 올바르지 않은 경우" +
      "exception 테스트")
  void coverImageDetailInfoTest5() {
    assertThatThrownBy(() -> new CoverImageDetailInfo(ONE_MB, MIN_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_HEIGHT + 10))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(INVALID_COVER_IMAGE_RATIO, MIN_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_HEIGHT + 10));
  }
}
