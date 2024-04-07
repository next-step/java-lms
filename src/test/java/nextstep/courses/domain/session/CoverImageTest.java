package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static nextstep.courses.domain.session.CoverImage.IMAGE_TITLE_IS_INCORRECT;
import static nextstep.courses.domain.session.CoverImageDetailInfo.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImageTest {

  @Test
  @DisplayName("정상 이미지 명, 이미지 확장자, 상세 정보를 입력한 경우" +
      "CoverImage 생성 테스트")
  void coverImageTest() {
    String imageTitle = "이미지명";
    ImageType imageType = ImageType.PNG;
    CoverImageDetailInfo coverImageDetailInfo = new CoverImageDetailInfo(ONE_MB, MIN_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_HEIGHT);

    CoverImage coverImage = new CoverImage(imageTitle, imageType, coverImageDetailInfo);
    assertThat(coverImage.getImageTitle()).isEqualTo(imageTitle);
    assertThat(coverImage.getImageType()).isEqualTo(imageType);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @DisplayName("이미지명이 null 또는 empty인 경우" +
      "exception 테스트")
  void coverImageTest2(String given) {
    ImageType imageType = ImageType.PNG;
    CoverImageDetailInfo coverImageDetailInfo = new CoverImageDetailInfo(ONE_MB, MIN_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_HEIGHT);

    assertThatThrownBy(() -> new CoverImage(given, imageType, coverImageDetailInfo))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(IMAGE_TITLE_IS_INCORRECT, given));
  }
}
