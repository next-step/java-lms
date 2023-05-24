package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ImageTypeTest {

  @Test
  void Enum_올바른_description_반환_테스트() {
    assertThat(ImageType.PNG.getDescription()).isEqualTo("png");
    assertThat(ImageType.JPEG.getDescription()).isEqualTo("jpeg");
    assertThat(ImageType.GIF.getDescription()).isEqualTo("gif");
  }

  @Test
  void String값을_통해_적절한_Enum_반환_테스트() {
    assertThat(ImageType.of("png")).isEqualTo(ImageType.PNG);
    assertThat(ImageType.of("jpeg")).isEqualTo(ImageType.JPEG);
    assertThat(ImageType.of("gif")).isEqualTo(ImageType.GIF);
  }


  @ParameterizedTest
  @ValueSource(strings = {"jpg", "JPG", "bmp", "BMP", "tiff", "TIFF"})
  void 지원하지_않는_확장자_입력시_예외발생_테스트(String extension) {
    assertThatThrownBy(() -> ImageType.of(extension))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("지원하지 않는 확장자 입니다.");
  }


}