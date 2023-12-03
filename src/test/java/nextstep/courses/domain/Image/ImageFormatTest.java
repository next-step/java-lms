package nextstep.courses.domain.Image;

import nextstep.courses.exception.ImageException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImageFormatTest {
    @Test
    @DisplayName("이미지 타입은 gif, jpg(jpeg 포함),, png, svg만 허용한다.")
    void find_확장자에러() {
        Assertions.assertThatThrownBy(() -> ImageFormat.findBy("txt"))
                .isInstanceOf(ImageException.class);
    }
}
