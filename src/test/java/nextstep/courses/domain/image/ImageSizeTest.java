package nextstep.courses.domain.image;

import nextstep.courses.FileSizeException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageSizeTest {
    @Test
    public void exceedImageSize() {
        assertThatThrownBy(() -> new ImageSize(1024 * 1024 + 1)).isInstanceOf(FileSizeException.class);
    }
}
