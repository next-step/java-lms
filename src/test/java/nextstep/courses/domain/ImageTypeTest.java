package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageTypeTest {
  @Test
  @DisplayName("지원되는 이미지 타입이면 true를 반환한다")
  void supported_image_type() {
    assertThat(ImageType.isSupported("image/jpeg")).isTrue();
    assertThat(ImageType.isSupported("image/png")).isTrue();
  }

  @Test
  @DisplayName("대소문자 구분 없이 지원한다")
  void case_insensitive() {
    assertThat(ImageType.isSupported("IMAGE/JPEG")).isTrue();
  }

  @Test
  @DisplayName("지원되지 않는 타입이면 false를 반환한다")
  void unsupported_type() {
    assertThat(ImageType.isSupported("application/pdf")).isFalse();
    assertThat(ImageType.isSupported("image/tiff")).isFalse();
  }

}
