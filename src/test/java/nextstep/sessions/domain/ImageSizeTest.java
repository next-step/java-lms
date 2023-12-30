package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageSizeTest {
    public static final ImageSize SIZE = new ImageSize(1000, 300, 200);

    @DisplayName("크기, 가로 사이즈, 세로 사이즈를 전달하면 이미지 사이즈 객체를 생성한다.")
    @Test
    void ImageSizeTest() {
        assertThat(new ImageSize(1000, 300, 200)).isInstanceOf(ImageSize.class);
    }

    @DisplayName("0 또는 최대 크기를 전달할 경우 IllegalArgumentException을 던진다.")
    @Test
    void sizeExceptionTest() {
        assertThatThrownBy(() -> new ImageSize(0, 300, 200))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new ImageSize(1_000_001, 300, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가로 300px 이하 또는 세로 200px 이하의 사이즈를 전달할 경우 IllegalArgumentException을 던진다.")
    @Test
    void widthHeightExceptionTest() {
        assertThatThrownBy(() -> new ImageSize(1000, 299, 200))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new ImageSize(1000, 300, 199))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
