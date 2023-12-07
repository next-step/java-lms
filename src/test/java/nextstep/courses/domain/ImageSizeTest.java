package nextstep.courses.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImageSizeTest {

  @Test
  @DisplayName("이미지 사이즈 비율 제한 테스트")
  public void image_size_limit() {
    // given
    double width = 400; double height = 200;
    // then
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new ImageSize(width,height));
  }

  @Test
  @DisplayName("이미지 높이 제한 테스트")
  public void image_height_limit() {
    // given
    double width = 400; double height = 199;
    // then
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new ImageSize(width,height));
  }


  @Test
  @DisplayName("이미지 길이 제한 테스트")
  public void image_width_limit() {
    // given
    double width = 299; double height = 200;
    // then
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new ImageSize(width,height));
  }

}
