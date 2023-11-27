package nextstep.courses.domain;

import nextstep.courses.exception.IncorrectRatioException;
import nextstep.courses.exception.SizeUnderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImageSizeTest {

    @Test
    @DisplayName("가로 세로 비율이 3:2가 아니면 예외 처리 된다")
    void newSize() {
        assertThrows(IncorrectRatioException.class, () -> new ImageSize(300, 300), "가로 세로 비율은 3:2 입니다.");
    }

    @Test
    @DisplayName("가로 세로 비율이 3:2면 size를 생성한다")
    void newSize2() {
        assertDoesNotThrow(() -> new ImageSize(300, 200));
    }

    @Test
    @DisplayName("세로가 200픽셀 미만이면 에러 처리 된다")
    void newSize3() {
        assertThrows(SizeUnderException.class, () -> new ImageSize(300L, 199L), "세로 픽셀은 200이상 입니다.");
    }

    @Test
    @DisplayName("가로가 300픽셀 미만이면 에러 처리 된다")
    void newSize4() {
        assertThrows(SizeUnderException.class, () -> new ImageSize(299L, 200L), "가로 픽셀은 300이상 입니다.");
    }

}
