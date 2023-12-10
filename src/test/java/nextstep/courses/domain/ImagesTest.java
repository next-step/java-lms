package nextstep.courses.domain;

import nextstep.courses.domain.image.Images;
import nextstep.courses.exception.ImagesSizeZeroException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

class ImagesTest {

    @DisplayName("강의 이미지는 최소 1장이 아닐 경우 예외가 발생한다.")
    @Test
    void throw_exception_if_images_size_is_less_than_one() {
        assertThatThrownBy(() -> new Images(Collections.emptyList()))
                .isInstanceOf(ImagesSizeZeroException.class);
    }
}
