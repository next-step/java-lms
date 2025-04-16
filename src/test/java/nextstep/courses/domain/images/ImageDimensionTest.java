package nextstep.courses.domain.images;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImageDimensionTest {

    @Test
    void widthOver300() {
        assertThrows(InvalidImageDimensionException.class, () -> new ImageDimension(299.9, 200.0));
    }

    @Test
    void heightOver200() {
        assertThrows(InvalidImageDimensionException.class, () -> new ImageDimension(300.0, 199.9));
    }

    @Test
    void widthHeightRatio3to2() {
        assertThrows(InvalidImageDimensionException.class, () -> new ImageDimension(300.0, 201.1));
    }
}
