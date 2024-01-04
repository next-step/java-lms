package nextstep.courses.domain;

import nextstep.courses.CannotApproveException;
import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.session.SessionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.payments.domain.PaymentTest.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SessionConditionTest {
    static final SessionCondition PAID_SESSION_CONDITION = new SessionCondition(800_000L, 120L, 0L);
    static final SessionCondition FREE_SESSION_CONDITION_1 = new SessionCondition(0L, 100L, 0L);
    private static final SessionCondition FREE_SESSION_CONDITION_2 = new SessionCondition(0L, 3L, 3L);

    @Test
    @DisplayName("checkPaidSession_무료강의_결제 금액 불일치 시 throw CannotEnrollException")
    void 무료() {
        assertDoesNotThrow(() -> FREE_SESSION_CONDITION_1.match(FREE_1));
        assertThatThrownBy(() -> FREE_SESSION_CONDITION_1.match(PAID_1))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("결제 금액이 일치하지 않습니다.");
    }

    @Test
    @DisplayName("checkPaidSession_유료강의_결제 금액 불칠치 시 throw CannotEnrollException")
    void 유료_결제금액_불일치() {
        assertThatThrownBy(() -> PAID_SESSION_CONDITION.match(PAID_2))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("결제 금액이 일치하지 않습니다.");
    }

    @Test
    @DisplayName("isFull_이미 수강정원이 꽉 찬 강의_throw CannotApproveException")
    void isFull() {
        assertThatThrownBy(FREE_SESSION_CONDITION_2::isFull)
                .isInstanceOf(CannotApproveException.class)
                .hasMessage("인원을 추가로 승인할 수 없습니다.");
    }
}
