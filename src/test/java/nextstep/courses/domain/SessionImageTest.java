package nextstep.courses.domain;

import nextstep.courses.InvalidImageFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SessionImageTest {

    @Test
    @DisplayName("이미지 크기가 0보다 작은 경우 Exception Throw")
    void zeroSize_ExceptionTest() {
        assertThrows(InvalidImageFormatException.class,
                () -> new SessionImage(0));
    }

    @Test
    @DisplayName("이미지 크기 1MB 이상인 경우 Exception Throw")
    void overSize_Test() {
        assertThrows(InvalidImageFormatException.class,
                () -> new SessionImage(1024 * 1025));
    }

    @Test
    @DisplayName("이미지 크기 0보다 크고 1MB 이상인 경우 정상")
    void available_ImageSize_Test() {
        assertDoesNotThrow(() -> new SessionImage(1024 * 1024));
    }

    @Test
    @DisplayName("이미지 타입이 gif, jpg(jpeg 포함), png, svg 이 아닌 경우 Exception Throw")
    void wrong_ImageType_Test() {
        assertThrows(InvalidImageFormatException.class,
                () -> SessionImage.imageTypeOf("jjpg"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"gif", "jpg", "jpeg", "png", "svg"})
    @DisplayName("이미지 타입이 gif, jpg(jpeg 포함), png, svg 이 아닌 경우 Exception Throw")
    void Available_ImageType_Test(String input) {
        assertDoesNotThrow(() -> SessionImage.imageTypeOf(input));

    }

    @Test
    @DisplayName("이미지의 width 300픽셀 미만인 경우 Exception Throw")
    void wrongWidthTest() {
        assertThrows(InvalidImageFormatException.class,
                () -> new SessionImage(301, 200));
    }

    @Test
    @DisplayName("이미지의 width 0이하인 경우 Exception Throw")
    void wrong_ZeroWidth_Test() {
        assertThrows(InvalidImageFormatException.class,
                () -> new SessionImage(0, 200));
    }

    @Test
    @DisplayName("이미지의 width 300픽셀 이상인 경우 정상")
    void availableWidthTest() {
        assertDoesNotThrow(() -> new SessionImage(300, 200));
    }

    @Test
    @DisplayName("이미지의 height 200픽셀 미만인 경우 Exception Throw")
    void wrongHeightTest() {
        assertThrows(InvalidImageFormatException.class,
                () -> new SessionImage(300, 199));
    }

    @Test
    @DisplayName("이미지의 height 0 이하인 경우 Exception Throw")
    void wrong_ZeroHeight_Test() {
        assertThrows(InvalidImageFormatException.class,
                () -> new SessionImage(300, 0));

    }

    @Test
    @DisplayName("이미지의 height 200픽셀 이상인 경우 정상")
    void availableHeightTest() {
        assertDoesNotThrow(() -> new SessionImage(300, 200));

    }

    @Test
    @DisplayName("이미지의 비율이 3:2가 아닌 경우 Exception Throw")
    void wrong_Ratio_Test() {
        assertThrows(InvalidImageFormatException.class,
                () -> new SessionImage(300, 201));
    }
}
