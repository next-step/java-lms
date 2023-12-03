package nextstep.courses.domain;

import nextstep.courses.domain.image.ImageSize;
import nextstep.courses.exception.NotValidSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ImageSizeTest {

    @DisplayName("이미지 세로가 300px 이하일 경우 예외가 발생한다.")
    @Test
    void throw_exception_when_height_is_under_300px() {
        double height = 299;
        double width = 200;

        assertThatThrownBy(() ->  new ImageSize(height, width))
                .isInstanceOf(NotValidSizeException.class);
    }

    @DisplayName("이미지 가로가 200px 이하일 경우 예외가 발생한다.")
    @Test
    void throw_exception_when_width_is_under_200px() {
        double height = 300;
        double width = 199;

        assertThatThrownBy(() ->  new ImageSize(height, width))
                .isInstanceOf(NotValidSizeException.class);
    }

    @DisplayName("이미지 비율이 3:2가 아닐 경우 예외가 발생한다.")
    @Test
    void throw_exception_when_ratio_is_not_3_2() {
        double height = 400;
        double width = 200;

        assertThatThrownBy(() ->  new ImageSize(height, width))
                .isInstanceOf(NotValidSizeException.class);
    }
}