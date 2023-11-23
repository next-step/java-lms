package nextstep.courses.domain;

import nextstep.courses.NotPositiveException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AmountTest {

    @Test
    @DisplayName("금액이 음수면 예외 처리 한다")
    void newAmount() {
        assertThrows(NotPositiveException.class, () -> new Amount(-1), "금액은 양수만 가능합니다.");
    }
}
