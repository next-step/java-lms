package nextstep.courses.domain.image;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImageTest {
    @Test
    void 이미지의_사이즈가_1MB를_초과할때() {
        assertThatThrownBy(() -> {
            new CoverImage(1024L * 1024L * 3, new ImagePixel(300, 200), ImageType.GIF);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}