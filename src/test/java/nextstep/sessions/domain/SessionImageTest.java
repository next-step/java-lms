package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SessionImageTest {
    public static final SessionImage IMAGE_PNG = new SessionImage(1000, 300, 200, "png");
    public static final SessionImage IMAGE_JPG = new SessionImage(1000, 300, 200, "jpg");

    @DisplayName("크기, 가로 사이즈, 세로 사이즈, 타입을 전달하면 이미지 객체를 생성한다.")
    @Test
    void imageTest() {
        assertThat(new SessionImage(1000, 300, 200, "png")).isInstanceOf(SessionImage.class);
    }

    @DisplayName("0 또는 최대 크기를 전달할 경우 IllegalArgumentException을 던진다.")
    @Test
    void sizeExceptionTest() {
        assertThatThrownBy(() -> new SessionImage(0, 300, 200, "png"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new SessionImage(1_000_001, 300, 200, "png"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가로 300px 이하 또는 세로 200px 이하의 사이즈를 전달할 경우 IllegalArgumentException을 던진다.")
    @Test
    void widthHeightExceptionTest() {
        assertThatThrownBy(() -> new SessionImage(1000, 299, 200, "png"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new SessionImage(1000, 300, 199, "png"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("지정되지 않은 이미지 타입을 전달할 경우 IllegalArgumentException을 던진다.")
    @Test
    void typeExceptionTest() {
        assertThatThrownBy(() -> new SessionImage(1000, 300, 200, "mp4"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
