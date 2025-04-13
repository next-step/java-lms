package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DimensionTest {

  @Test
  @DisplayName("정상적인 width:height 비율(3:2)과 크기이면 유효하다.")
  void valid_dimension() {
    Dimension dimension = new Dimension(600, 400); // 3:2
    assertThat(dimension.isValid()).isTrue();
  }

  @Test
  @DisplayName("너비가 300 미만이면 유효하지 않다.")
  void invalid_width() {
    Dimension dimension = new Dimension(299, 200);
    assertThat(dimension.isValid()).isFalse();
  }

  @Test
  @DisplayName("높이가 200 미만이면 유효하지 않다.")
  void invalid_height() {
    Dimension dimension = new Dimension(300, 199);
    assertThat(dimension.isValid()).isFalse();
  }

  @Test
  @DisplayName("비율이 3:2가 아니면 유효하지 않다.")
  void invalid_ratio() {
    Dimension dimension = new Dimension(400, 200); // 2:1
    assertThat(dimension.isValid()).isFalse();
  }

}
