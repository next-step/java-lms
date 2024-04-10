package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    @Test
    @DisplayName("이미지 크키가 1MB보다 크면 예외가 발생한다")
    void size_exception_test() {
        assertThrows(IllegalArgumentException.class, () -> new Image(1024 * 2, 600, 400, ImageType.JPG));
    }

    @Test
    @DisplayName("이미지 너비, 높이가 기준보다 작을 경우 예외가 발생한다")
    void min_width_heigth_exception_test() {
        assertThrows(IllegalArgumentException.class, () -> new Image(1024, 100, 100, ImageType.JPG));
    }

    @Test
    @DisplayName("이미지 비율이 3:2가 아니면 예외가 발생한다")
    void ratio_exception_test() {
        assertThrows(IllegalArgumentException.class, () -> new Image(1024, 600, 500, ImageType.JPG));
    }

}
