package nextstep.courses.domain.image;

import nextstep.courses.InvalidImageFileException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageSizeTest {
    @Test
    void 이미지의_크기는_1MB_이하이다() {
        assertThatThrownBy(() -> new ImageSize(1025))
                .isInstanceOf(InvalidImageFileException.class);
    }
}
