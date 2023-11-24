package nextstep.courses.domain;

import nextstep.courses.exception.IncorrectRatioException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SizeTest {

    @Test
    @DisplayName("가로 세로 비율이 3:2가 아니면 예외 처리 된다")
    void newSize() {
        assertThrows(IncorrectRatioException.class, () -> new Size(new Width(300), new Height(300)), "가로 세로 비율은 3:2 입니다.");
    }

    @Test
    @DisplayName("가로 세로 비율이 3:2면 size를 생성한다")
    void newSize2() {
        assertDoesNotThrow(() -> new Size(new Width(300), new Height(200)));
    }

}
