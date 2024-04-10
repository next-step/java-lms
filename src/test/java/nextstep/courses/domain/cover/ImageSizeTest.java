package nextstep.courses.domain.cover;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ImageSizeTest {

    @Test
    void 이미지의_크기는_1MB_이하만_가능하다() {
        ImageSize imageSize = new ImageSize(1);

        Assertions.assertThat(imageSize.getValue()).isEqualTo(1);
    }
}
