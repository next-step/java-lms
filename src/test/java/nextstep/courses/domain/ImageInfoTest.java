package nextstep.courses.domain;

import nextstep.courses.exception.ImageSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageInfoTest {

    @Test
    @DisplayName("이미지 크기가 1MB를 초과하면 예외가 발생한다. ")
    void 이미지_크기_체크() {
        assertThatThrownBy(() -> new ImageInfo(ImageType.GIF, 2L, 0,0))
                .isInstanceOf(ImageSizeException.class);
    }
}
