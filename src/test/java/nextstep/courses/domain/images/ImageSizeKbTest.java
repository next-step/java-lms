package nextstep.courses.domain.images;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImageSizeKbTest {

    @Test
    void lessThanOrEqualToZero() {
        double value = 0;
        assertThrows(IllegalArgumentException.class, () -> new ImageSizeKb(value));
    }

    @Test
    void largeValue() {
        double value = 1001;
        assertThrows(IllegalArgumentException.class, () -> new ImageSizeKb(value));
    }
}
