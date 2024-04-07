package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionStrategyTest {

    @Test
    @DisplayName("[성공] 무료 강의는 최대 수강 인원 제한이 없다.")
    void 무료_강의() {

    }

    @Test
    @DisplayName("[성공] 유료 강의는 강의 최대 수강 인원을 초과할 수 없다.")
    void 유료_강의_수강인원() {

    }

    @Test
    @DisplayName("[실패] 유료 강의의 최대 수강인원을 초과하면 SessionCapacityExceedException 예외가 발생한다.")
    void 유료_강의_수강인원_초과() {

    }

    @Test
    @DisplayName("[성공] 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    void 유료_강의_수강료() {

    }

    @Test
    @DisplayName("[실패] 유료 강의의 결제 금액과 수강료가 일치하지 않으면 MismatchedPaymentException 예외가 발생한다.")
    void 유료_강의_수강료_불일치() {

    }

}
