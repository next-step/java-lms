package nextstep.sessions.domain.image;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ImageTypeTest {

    @Test
    void whenValidExtension_thenReturnsImageType() {
        assertThat(ImageType.from("jpg")).isEqualTo(ImageType.JPG);
        assertThat(ImageType.from("JPEG")).isEqualTo(ImageType.JPEG);
        assertThat(ImageType.from("png")).isEqualTo(ImageType.PNG);
    }

    @Test
    void whenInvalidExtension_thenThrows() {
        assertThatThrownBy(() -> ImageType.from("exe"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenBlank_thenThrows() {
        assertThatThrownBy(() -> ImageType.from(""))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
