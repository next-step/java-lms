package nextstep.courses.domain.image;

import nextstep.courses.ImageDimensionException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ImageDimensionTest {
    @Test
    void exceedWidth() {
        assertThatThrownBy(() -> new ImageDimension(299, 200)).isInstanceOf(ImageDimensionException.class);
    }

    @Test
    void exceedHeight() {
        assertThatThrownBy(() -> new ImageDimension(300, 199)).isInstanceOf(ImageDimensionException.class);
    }

    @Test
    void invalidImageRatio() {
        assertThatThrownBy(() -> new ImageDimension(400, 300)).isInstanceOf(ImageDimensionException.class);
    }

    @Test
    void validImageRatio() {
        assertDoesNotThrow(() -> new ImageDimension(300, 200));
    }
}
