package nextstep.image;

import nextstep.image.domain.Image;
import nextstep.image.domain.ImageType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageTest {

    @Test
    void 이미지_크기는_1MB_이하여야_한다() {
        assertThatThrownBy(() -> Image.of(Image.MIN_WIDTH, Image.MIN_HEIGHT, ImageType.JPG, 1025))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 크기는 1MB 이하여야 한다.");
    }
}
