package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionImageTest {
    public static final SessionImage IMAGE_PNG = new SessionImage(ImageSizeTest.SIZE, "png");
    public static final SessionImage IMAGE_JPG = new SessionImage(ImageSizeTest.SIZE, "jpg");

    @DisplayName("이미지 사이즈, 타입을 전달하면 이미지 객체를 생성한다.")
    @Test
    void imageTest() {
        assertThat(new SessionImage(ImageSizeTest.SIZE, "png")).isInstanceOf(SessionImage.class);
    }


    @DisplayName("지정되지 않은 이미지 타입을 전달할 경우 IllegalArgumentException을 던진다.")
    @Test
    void typeExceptionTest() {
        assertThatThrownBy(() -> new SessionImage(ImageSizeTest.SIZE, "mp4"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
