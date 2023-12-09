package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.PaidType;
import nextstep.sessions.domain.exception.SessionsException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PayInfoTest {

    @Test
    void 유료_강의_결제_금액_불일치() {
        PayInfo payInfo = new PayInfo(PaidType.PAID, 800000L);
        Payment payment = new Payment(1L, 2L, 3L, 799999L);

        assertThatThrownBy(() -> payInfo.validatePayment(payment))
            .isInstanceOf(SessionsException.class)
            .hasMessage("수강료와 결제한 금액이 다릅니다.");
    }

    @Test
    void 수강료_음수() {
        assertThatThrownBy(() -> new PayInfo(PaidType.PAID, -1000L))
            .isInstanceOf(SessionsException.class)
            .hasMessage("수강료는 0 이상이어야 합니다.");
    }

    @Test
    void 수강료가_0인_유료강의() {
        assertThatThrownBy(() -> new PayInfo(PaidType.PAID, 0L))
            .isInstanceOf(SessionsException.class)
            .hasMessage("유료 강의는 수강료를 입력해야합니다.");
    }

    @Test
    void 수강료가_0초과인_무료강의() {
        assertThatThrownBy(() -> new PayInfo(PaidType.FREE, 1000L))
            .isInstanceOf(SessionsException.class)
            .hasMessage("무료 강의는 수강료를 입력할 수 없습니다.");
    }
}
