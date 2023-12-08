package nextstep.courses.domain.image;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ImageTypeTest {
    @Test
    void normalImageTypeTest() {
        assertThat(ImageType.of("gif")).isEqualTo(ImageType.GIF);
    }

    @Test
    void failedImageTypeTest() {
        assertThatThrownBy(() -> {
            ImageType.of("xxx");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
