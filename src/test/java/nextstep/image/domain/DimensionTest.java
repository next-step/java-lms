package nextstep.image.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DimensionTest {

  @Test
  @DisplayName("정상적인 width:height 비율(3:2)이 아니면 예외가 발생한다.")
  void valid_dimension() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> new Dimension(400, 200))
        .withMessageContaining("비율이 맞지 않습니다. 3:2");
  }

  @Test
  @DisplayName("너비가 300 미만이면 예외가 발생한다.")
  void invalid_width() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> new Dimension(299, 200))
        .withMessageContaining("너비는 300 이상이어야 합니다.");
  }

  @Test
  @DisplayName("높이가 200 미만이면 예외가 발생한다.")
  void invalid_height() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> new Dimension(300, 199))
        .withMessageContaining("높이는 200 이상이어야 합니다.");
  }


}
