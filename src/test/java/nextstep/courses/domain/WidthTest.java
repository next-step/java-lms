package nextstep.courses.domain;

import nextstep.courses.exception.SizeUnderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class WidthTest {

    @Test
    @DisplayName("가로가 300픽셀 미만이면 에러 처리 된다")
    void newWidth() {
        assertThrows(SizeUnderException.class, () -> new Width(299L), "가로 픽셀은 300이상 입니다.");
    }
}
