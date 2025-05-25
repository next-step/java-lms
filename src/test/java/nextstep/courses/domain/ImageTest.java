package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImageTest {

    @Test
    @DisplayName("이미지 크기는 1MB 이하여야 한다")
    void 이미지_크기는_1MB_이하다() {
        long validSize = 1_048_576L; // 1MB

        assertThatThrownBy(() -> new SessionImage(validSize, SessionImageType.gif, 300, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미지 크기는 1MB 이하여야 합니다.");
    }

    public void 이미지_타입은_gif_jpg_png_svg만_허용한다() {
        assertThatThrownBy(() -> SessionImageType.of("doc"))
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("이미지의 width는 300 이상, height는 200 이상이어야 한다")
    void 이미지의_width는_300이상_height는_200이상이어야한다() {
        assertThatThrownBy(() -> new SessionImage(500_000L, SessionImageType.gif, 299, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미지의 최소 너비는 300px");

        assertThatThrownBy(() -> new SessionImage(500_000L, SessionImageType.gif, 300, 199))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미지의 최소 높이는 200px");
    }
    
}
