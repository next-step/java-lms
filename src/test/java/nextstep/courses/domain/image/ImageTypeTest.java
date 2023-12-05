package nextstep.courses.domain.image;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageTypeTest {
    @Test
    void 파일확장자가_올바르지_않을때() {
        assertThatThrownBy(() -> {
            ImageType.of("xxx");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}