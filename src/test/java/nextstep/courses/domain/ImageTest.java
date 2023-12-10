package nextstep.courses.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class ImageTest {

    @Test
    void 이미지_타입은_gif_jpg_jpeg_포함__png_svg만_허용한다() {
        // given
        int size = 1;

        // when
        Image image = new Image(size, ImageType.JPG);

        // then
        assertThat(image.getType()).isEqualTo(ImageType.JPG);
    }

    @Test
    void 이미지_크기는_1MB_이하여야_한다() {
        // given
        int size = 2;

        // when, then
        assertThatThrownBy(() -> new Image(size, ImageType.GIF)).isInstanceOf(
            IllegalArgumentException.class);
    }
}
