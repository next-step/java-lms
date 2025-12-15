package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageTypeTest {

    @Test
    @DisplayName("유효한 확장자")
    void from_valid_extension() {
        assertThat(ImageType.from("gif")).isEqualTo(ImageType.GIF);
        assertThat(ImageType.from("GIF")).isEqualTo(ImageType.GIF);
        assertThat(ImageType.from("jpg")).isEqualTo(ImageType.JPG);
        assertThat(ImageType.from("jpeg")).isEqualTo(ImageType.JPG);
    }

    @Test
    @DisplayName("유효하지 않은 확장자")
    void from_invalid_extension() {
        assertThatThrownBy(() -> ImageType.from("txt"))
                .isInstanceOf(IllegalArgumentException.class);
    }


}