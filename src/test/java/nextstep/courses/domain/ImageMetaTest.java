package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ImageMetaTest {

    @Test
    void 파일_크기_예외() {
        assertThatThrownBy(() ->new ImageMeta(300, 150, 2_000_000, "png"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 확장자_예외() {
        assertThatThrownBy(() -> new ImageMeta(300, 150, 5_000, "webp"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
