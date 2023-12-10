package nextstep.courses.domain.image;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.image.Type;
import nextstep.courses.exception.InvalidImageException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImageTest {

    private static final int ONE_MB = 1024 * 1024;

    @Test
    @DisplayName("이미지 크기는 1MB 이하여야 한다")
    void image_size() {
        assertThatThrownBy(() -> {
            Image image = new Image(ONE_MB + 1, Type.value("gif"), 301, 201);
        }).isInstanceOf(InvalidImageException.class);
    }


    @Test
    @DisplayName("이미지의 width는 300픽셀, height는 200픽셀 이상이어야 한다")
    void image_width_and_height() {
        assertThatThrownBy(() -> {
            Image image = new Image(ONE_MB, Type.value("gif"), 299, 199);
        }).isInstanceOf(InvalidImageException.class);
        assertThatThrownBy(() -> {
            Image image = new Image(ONE_MB, Type.value("gif"), 300, 199);
        }).isInstanceOf(InvalidImageException.class);
        assertThatThrownBy(() -> {
            Image image = new Image(ONE_MB, Type.value("gif"), 299, 200);
        }).isInstanceOf(InvalidImageException.class);
    }

    @Test
    @DisplayName("width와 height의 비율은 3:2여야 한다")
    void image_width_and_height_ratio() {
        assertThatThrownBy(() -> {
            Image image = new Image(ONE_MB, Type.value("gif"), 300, 207);
        }).isInstanceOf(InvalidImageException.class);
    }
}
