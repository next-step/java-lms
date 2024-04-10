package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTypeTest {

    @Test
    @DisplayName("허용되지 않은 이미지 타입의 경우 예외가 발생한다.")
    void type_exception_test() {
        assertThrows(IllegalArgumentException.class, () -> ImageType.of("pptx"));
    }

}
