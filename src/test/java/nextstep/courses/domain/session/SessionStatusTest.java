package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionStatusTest {

    @Test
    @DisplayName("준비중, 모집중, 종료 이외의 상태인 경우 예외를 던진다.")
    void NotExistedSessionStatus_Exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> SessionStatus.from("notExisted"));
    }
}
