package nextstep.courses.domain.session.image;

import nextstep.courses.ImageSizeExceededException;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.image.ImageFileSize.IMAGE_SIZE_EXCEED_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageFileSizeTest {

    @Test
    void create() {
        ImageFileSize actual = new ImageFileSize(1024 * 1024);
        ImageFileSize expected = new ImageFileSize(1024 * 1024);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void create_1MB_초과() {
        assertThatThrownBy(() -> {
            new ImageFileSize(1024 * 1024 + 1);

        }).isInstanceOf(ImageSizeExceededException.class)
                .hasMessage(IMAGE_SIZE_EXCEED_MESSAGE);
    }
}
