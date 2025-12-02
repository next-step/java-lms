package nextstep.courses.domain.image;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SessionCoverImageTest {
  private static final int WIDTH = 300, HEIGHT = 200;
  private static final long VALID_BYTES = 1024 * 500; // 500KB

  @ParameterizedTest
  @CsvSource({"gif","jpg","jpeg", "png", "svg"})
  void 허용된_확장자(String extension){
    assertDoesNotThrow(() -> new SessionCoverImage(WIDTH, HEIGHT, extension, VALID_BYTES));
  }

  @ParameterizedTest
  @CsvSource({"webp"})
  void 허용하지않은_확장자는_예외(String extension){
    assertThatThrownBy(() -> new SessionCoverImage(WIDTH, HEIGHT, extension, VALID_BYTES))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("지원하지 않는 이미지 확장자입니다: " + extension);
  }


}