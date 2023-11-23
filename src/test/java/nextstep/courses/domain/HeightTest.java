package nextstep.courses.domain;

import nextstep.courses.SizeUnderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class HeightTest {

    @Test
    @DisplayName("가로가 300픽셀 미만이면 에러 처리 된다")
    void newWidth() {
        assertThrows(SizeUnderException.class, () -> new Height(199L), "세로 픽셀은 200이상 입니다.");
    }
}
