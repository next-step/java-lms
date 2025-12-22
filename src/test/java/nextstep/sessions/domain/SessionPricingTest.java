package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class SessionPricingTest {

    public static final SessionPricing FREE_SP = new SessionPricing(false, 0);
    public static final SessionPricing PAID_SP = new SessionPricing(true, 100_000);
    public static final SessionPricing PAID_SP_2 = new SessionPricing(true, 1_000);

    @Test
    void freeSession_feeIsZero() {
        SessionPricing freeSessionPricing = new SessionPricing(false, 0);
        assertThat(freeSessionPricing.fee()).isEqualTo(0);
    }

    @Test
    void freeSession_invalidFee_throwsException() {
        assertThatThrownBy(() -> new SessionPricing(false, 500_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("무료 강의는 0원 이어야 합니다");
    }

    @Test
    void paidSession_feeIsPositive() {
        SessionPricing paidSessionPricing = new SessionPricing(true, 100_000);
        assertThat(paidSessionPricing.fee()).isEqualTo(100_000);
    }

    @Test
    void paidSession_invalidFee_throwsException() {
        assertThatThrownBy(() -> new SessionPricing(true, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유료 강의는 0원 초과 여야 합니다");
    }
}
