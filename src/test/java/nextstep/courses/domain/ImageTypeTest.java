package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageTypeTest {

    @Test
    @DisplayName("유효한 확장자")
    void from_valid_extension() {
        assertThat(ImageType.fromFileName("test.gif")).isEqualTo(ImageType.GIF);
        assertThat(ImageType.fromFileName("test.GIF")).isEqualTo(ImageType.GIF);
        assertThat(ImageType.fromFileName("test.jpg")).isEqualTo(ImageType.JPG);
        assertThat(ImageType.fromFileName("test.jpeg")).isEqualTo(ImageType.JPG);
    }

    @Test
    @DisplayName("유효하지 않은 확장자")
    void from_invalid_extension() {
        assertThatThrownBy(() -> ImageType.fromFileName("test.txt"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("확장자가 없는 이름은 유효하지 않다")
    void fromFileName_invalid_fileName() {
        assertThatThrownBy(() -> ImageType.fromFileName("test"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}