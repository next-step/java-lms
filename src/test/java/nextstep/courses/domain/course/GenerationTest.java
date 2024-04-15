package nextstep.courses.domain.course;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.course.Generation.NEGATIVE_NUMBER_OR_ZERO_IS_NOT_ALLOWED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GenerationTest {

  @Test
  @DisplayName("기수 정상 생성 테스트")
  void generation_create_test() {
    Long given = 1L;
    Generation generation = new Generation(given);
    assertThat(generation.isSame(given)).isTrue();
  }

  @Test
  @DisplayName("기수 값을 음수로 넣은 경우" +
      "exception 테스트")
  void generation_negative_number_exception_test() {
    Long given = -100L;
    assertThatThrownBy(() -> new Generation(given))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(NEGATIVE_NUMBER_OR_ZERO_IS_NOT_ALLOWED, given));
  }

  @Test
  @DisplayName("기수 값을 0으로 넣은 경우" +
      "exception 테스트")
  void generation_zero_number_exception_test() {
    Long given = 0L;
    assertThatThrownBy(() -> new Generation(given))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(NEGATIVE_NUMBER_OR_ZERO_IS_NOT_ALLOWED, given));
  }
}
