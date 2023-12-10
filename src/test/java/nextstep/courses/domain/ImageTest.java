package nextstep.courses.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class ImageTest {

    @Test
    void 이미지_타입은_gif_jpg_jpeg_포함__png_svg만_허용한다() {
        // given
        int size = 1;
        ImageSize imageSize = new ImageSize(300, 200);
        // when
        Image image = new Image(imageSize, ImageType.JPG);

        // then
        assertThat(image.getType()).isEqualTo(ImageType.JPG);
    }


}
