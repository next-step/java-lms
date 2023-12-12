package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.payments.domain.PaymentTest.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SessionPaymentConditionTest {
    private static final SessionPaymentCondition PAID_SESSION_PAYMENT_CONDITION = new SessionPaymentCondition(800_000L, 120L);
    private static final SessionPaymentCondition FREE_SESSION_PAYMENT_CONDITION = new SessionPaymentCondition();

    @Test
    @DisplayName("checkPaidSession_무료강의_결제 금액 불일치 시 throw CannotEnrollException")
    void 무료() {
        assertDoesNotThrow(() -> FREE_SESSION_PAYMENT_CONDITION.checkPaidSession(FREE, 120L));
        assertThatThrownBy(() -> FREE_SESSION_PAYMENT_CONDITION.checkPaidSession(PAID_1, 120L))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("결제 금액이 일치하지 않습니다.");
    }

    @Test
    @DisplayName("checkPaidSession_유료강의_maxUser 초과 시 throw CannotEnrollException")
    void 최대수강인원_초과() {
        assertDoesNotThrow(() -> PAID_SESSION_PAYMENT_CONDITION.checkPaidSession(PAID_1, 119L));
        assertThatThrownBy(() -> PAID_SESSION_PAYMENT_CONDITION.checkPaidSession(PAID_1, 120L))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("수강 인원을 초과했습니다.");
    }

    @Test
    @DisplayName("checkPaidSession_유료강의_결제 금액 불칠치 시 throw CannotEnrollException")
    void 유료_결제금액_불일치() {
        assertThatThrownBy(() -> PAID_SESSION_PAYMENT_CONDITION.checkPaidSession(PAID_2, 119L))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("결제 금액이 일치하지 않습니다.");
    }
}