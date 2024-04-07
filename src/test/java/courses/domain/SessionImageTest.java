package courses.domain;

import nextstep.courses.domain.SessionImage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionImageTest {
  @Test
  void 잘못된_세션_이미지_width() {
    assertThatThrownBy(() -> new SessionImage(1L, 200, 200, "gif", 512, "test"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이미지 width는 300px 이상이어야 합니다.");
  }

  @Test
  void 잘못된_세션_이미지_height() {
    assertThatThrownBy(() -> new SessionImage(1L, 400, 100, "gif", 512, "test"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이미지 height는 200px 이상이어야 합니다.");
  }

  @Test
  void 잘못된_세션_이미지_비율() {
    assertThatThrownBy(() -> new SessionImage(1L, 300, 250, "gif", 512, "test"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이미지 width, height 비율은 3:2여야 합니다.");
  }

  @Test
  void 잘못된_세션_이미지_확장자() {
    assertThatThrownBy(() -> new SessionImage(1L, 300, 200, "txt", 512, "test"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("잘못된 이미지 확장자입니다.");
  }

  @Test
  void 잘못된_세션_이미지_크기() {
    assertThatThrownBy(() -> new SessionImage(1L, 300, 200, "jpg", 2048, "test"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이미지 크기는 1MB 이하여야 합니다.");
  }
}
