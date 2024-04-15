package nextstep.courses.domain;

import nextstep.courses.domain.image.Image;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImageTest {
    @Test
    public void 이미지_크기는_1MB_이하여야한다() {
        assertDoesNotThrow(() -> {
            createImage(1024, "gif", 300, 200);
        });
    }

    @Test
    public void 이미지_크기는_1MB를_초과할수없다() {
        assertThrows(IllegalArgumentException.class, () -> {
            createImage(1025, "gif", 300, 200);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"gif", "jpg", "jpeg", "png", "svg"})
    public void 이미지_타입은_gif_jpg_jpeg_png_svg만허용한다(String type) {
        assertDoesNotThrow(() -> {
            createImage(1024, type, 300, 200);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"zip", "mp4"})
    public void 이미지_타입은_gif_jpg_jpeg_png_svg가_아닌경우_허용하지않는다(String type) {
        assertThrows(IllegalArgumentException.class, () -> {
            createImage(1024, type, 300, 200);
        });
    }

    @Test
    public void 이미지의_width는_300픽셀_height는_200픽셀_이상이어야하며_width와_height의_비율은_3대2만_허용한다() {
        assertDoesNotThrow(() -> {
            createImage(1024, "gif", 300, 200);
        });
    }

    @Test
    public void 이미지의_width는_300픽셀_height는_200픽셀_이상이어야하며_width와_height의_비율은_3대2가_아닌경우_허용하지않는다() {
        assertThrows(IllegalArgumentException.class, () -> {
            createImage(1024, "gif", 280, 190);
        });
    }

    private Image createImage(int size, String type, int width, int height) {
        return new Image(size, type, width, height);
    }
}
