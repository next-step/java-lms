package nextstep.courses.domain.image;

import nextstep.courses.InvalidImageFileException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageFileExtensionTest {
    @Test
    void 이미지_확장자는_gif_jpg_png_svg만_가능하다() {
        assertThatThrownBy(() -> ImageFileExtension.from("txt"))
                .isInstanceOf(InvalidImageFileException.class);
    }
}
