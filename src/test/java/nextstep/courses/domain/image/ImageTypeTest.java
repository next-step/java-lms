package nextstep.courses.domain.image;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageTypeTest {
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  "})
    public void NullAndEmptyFileName(String fileName) {
        assertThat(ImageType.extract(fileName)).isEqualTo(ImageType.UNKNOWN);
    }

    @ParameterizedTest
    @CsvSource({
            "a.png, PNG",
            "b.jpg, JPG",
            "c.jpeg, JPEG",
            "d.svg, SVG"
    })
    public void extractType(String fileName, ImageType imageType) {
        assertThat(ImageType.extract(fileName)).isEqualTo(imageType);
    }

    @Test
    public void notAllowedFileExtension() {
        assertThat(ImageType.extract("a.md")).isEqualTo(ImageType.UNKNOWN);
    }
}
