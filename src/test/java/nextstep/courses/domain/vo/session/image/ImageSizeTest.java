package nextstep.courses.domain.vo.session.image;

import nextstep.courses.ImageSizeExceededException;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.vo.session.image.ImageSize.IMAGE_SIZE_EXCEED_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageSizeTest {

    @Test
    void create() {
        ImageSize actual = new ImageSize(1024 * 1024);
        ImageSize expected = new ImageSize(1024 * 1024);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void create_1MB_초과() {
        assertThatThrownBy(() -> {
            new ImageSize(1024 * 1024 + 1);

        }).isInstanceOf(ImageSizeExceededException.class)
                .hasMessage(IMAGE_SIZE_EXCEED_MESSAGE);
    }
}
