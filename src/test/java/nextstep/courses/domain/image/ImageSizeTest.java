package nextstep.courses.domain.image;

import nextstep.courses.ImageDimensionException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ImageSizeTest {
    @Test
    void exceedWidth() {
        assertThatThrownBy(() -> new ImageSize(299, 200)).isInstanceOf(ImageDimensionException.class);
    }

    @Test
    void exceedHeight() {
        assertThatThrownBy(() -> new ImageSize(300, 199)).isInstanceOf(ImageDimensionException.class);
    }

    @Test
    void invalidImageRatio() {
        assertThatThrownBy(() -> new ImageSize(400, 300)).isInstanceOf(ImageDimensionException.class);
    }

    @Test
    void validImageRatio() {
        assertDoesNotThrow(() -> new ImageSize(300, 200));
    }
}
