package nextstep.courses.domain.image;

import nextstep.courses.ImageDimensionException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ImageSizeTest {
    @Test
    void validateWidth() {
        assertThatThrownBy(() -> new ImageSize(301, 200)).isInstanceOf(ImageDimensionException.class);
    }
}
