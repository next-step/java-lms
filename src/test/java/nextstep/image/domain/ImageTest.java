package nextstep.image.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageTest {
  @Test
  @DisplayName("정상적인 이미지 속성이면 생성된다.")
  void valid_image() {
    ImageMeta meta = new ImageMeta("lecture.jpg", ImageType.JPEG, 500_000);
    Dimension dimension = new Dimension(300, 200);

    Image image = new Image(meta, dimension);

    assertThat(image).isNotNull();
  }

  @Test
  @DisplayName("1MB를 초과하면 예외가 발생한다.")
  void image_size_exceeds_limit() {
    ImageMeta meta = new ImageMeta("big.jpg", ImageType.PNG, 1_048_577);
    Dimension dimension = new Dimension(300, 200);

    assertThatIllegalArgumentException()
        .isThrownBy(() -> new Image(meta, dimension))
        .withMessageContaining("이미지 크기는 1MB 이하여야 합니다.");
  }
}
