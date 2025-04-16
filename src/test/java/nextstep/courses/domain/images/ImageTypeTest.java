package nextstep.courses.domain.images;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImageTypeTest {

    @ParameterizedTest
    @ValueSource(strings = {"image/jpeg", "image/png", "image/gif", "image/svg+xml"})
    void validMimeType(String mimeType) {
        ImageType imageType = ImageType.fromMimeType(mimeType);
        assertNotNull(imageType);
    }

    @ParameterizedTest
    @ValueSource(strings = {"image/bmp", "image/tiff", "image/webp"})
    void invalidMimeType(String mimeType) {
        assertThrows(IllegalArgumentException.class, () -> ImageType.fromMimeType(mimeType));
    }
}
