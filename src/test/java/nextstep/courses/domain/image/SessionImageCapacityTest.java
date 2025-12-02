package nextstep.courses.domain.image;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class SessionImageCapacityTest {

  @Test
  void 크기_1MB이하_정상생성() {
    assertThatCode(() -> new SessionImageCapacity(1024 * 1024))
        .doesNotThrowAnyException();
  }

  @Test
  void 크기_1MB초과_예외() {
    assertThatThrownBy(() -> new SessionImageCapacity(1024 * 1024 + 1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("이미지 크기는 1MB 이하여야 합니다.");
  }

  @Test
  void 크기_0이하_예외() {
    assertThatThrownBy(() -> new SessionImageCapacity(0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("이미지 크기는 0보다 커야 합니다.");
  }

  @Test
  void KB단위_생성() {
    assertThatCode(() -> SessionImageCapacity.ofKB(500))
        .doesNotThrowAnyException();
  }

  @Test
  void MB단위_1MB_정상생성() {
    assertThatCode(() -> SessionImageCapacity.ofMB(1))
        .doesNotThrowAnyException();
  }

  @Test
  void MB단위_2MB_예외() {
    assertThatThrownBy(() -> SessionImageCapacity.ofMB(2))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("이미지 크기는 1MB 이하여야 합니다.");
  }
}