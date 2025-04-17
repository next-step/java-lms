package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionImageTest {
    public static SessionImage createImage(int width, int height, String path) {
        byte[] file = new byte[width * height];
        return new SessionImage(path, width, height, file);
    }

    public static SessionImage createImage(int width, int height) {
        return createImage(width, height, "image.png");
    }

    public static SessionImage createImage(String path) {
        return createImage(300, 200, path);
    }

    @Test
    @DisplayName("이미지 크기는 1MB 이하여야 한다.")
    void imageSizeShouldBeOneMB() {
        assertThatCode(() -> createImage(300, 200)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이미지 크기는 1MB 초과하면 에러가 발생한다.")
    void imageSizeExceedOneMB() {
        assertThatThrownBy(() -> createImage(1500, 1000)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다.")
    void imageTypeShouldBeCorrect() {
        assertThatCode(() -> createImage("image.gif")).doesNotThrowAnyException();
        assertThatCode(() -> createImage("image.jpg")).doesNotThrowAnyException();
        assertThatCode(() -> createImage("image.jpeg")).doesNotThrowAnyException();
        assertThatCode(() -> createImage("image.png")).doesNotThrowAnyException();
        assertThatCode(() -> createImage("image.svg")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이미지 타입은 gif, jpg(jpeg 포함), png, svg가 아니면 에러가 발생한다.")
    void imageTypeInCorrect() {
        assertThatThrownBy(() -> createImage("image.xlsx")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이미지의 width는 300픽셀 이상이어야 한다.")
    void imageWidthShouldBeCorrect() {
        assertThatThrownBy(() -> createImage(200, 200)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이미지의 height는 200픽셀 이상이어야 한다.")
    void imageHeightShouldBeCorrect() {
        assertThatThrownBy(() -> createImage(300, 150)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이미지의 width와 height의 비율은 3:2여야 한다.")
    void imageWidthAndHeightShouldBeCorrect() {
        assertThatThrownBy(() -> createImage(400, 200)).isInstanceOf(IllegalArgumentException.class);
    }

}
