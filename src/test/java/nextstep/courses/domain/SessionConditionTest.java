package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.session.SessionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.payments.domain.PaymentTest.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SessionConditionTest {
    private static final SessionCondition PAID_SESSION_CONDITION = new SessionCondition(800_000L, 120L);
    private static final SessionCondition FREE_SESSION_CONDITION = new SessionCondition();

    @Test
    @DisplayName("checkPaidSession_무료강의_결제 금액 불일치 시 throw CannotEnrollException")
    void 무료() {
        assertDoesNotThrow(() -> FREE_SESSION_CONDITION.match(FREE, 120L));
        assertThatThrownBy(() -> FREE_SESSION_CONDITION.match(PAID_1, 120L))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("결제 금액이 일치하지 않습니다.");
    }

    @Test
    @DisplayName("checkPaidSession_유료강의_maxUser 초과 시 throw CannotEnrollException")
    void 최대수강인원_초과() {
        assertDoesNotThrow(() -> PAID_SESSION_CONDITION.match(PAID_1, 119L));
        assertThatThrownBy(() -> PAID_SESSION_CONDITION.match(PAID_1, 120L))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("수강 인원을 초과했습니다.");
    }

    @Test
    @DisplayName("checkPaidSession_유료강의_결제 금액 불칠치 시 throw CannotEnrollException")
    void 유료_결제금액_불일치() {
        assertThatThrownBy(() -> PAID_SESSION_CONDITION.match(PAID_2, 119L))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("결제 금액이 일치하지 않습니다.");
    }
}
