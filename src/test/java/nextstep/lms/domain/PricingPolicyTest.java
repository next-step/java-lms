package nextstep.lms.domain;

import nextstep.lms.enums.PricingTypeEnum;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PricingPolicyTest {
    private PricingPolicy freePolicy;
    private PricingPolicy paidPolicy;

    @BeforeEach
    void setting() {
        this.freePolicy = new PricingPolicy(PricingTypeEnum.FREE, 0L);
        this.paidPolicy = new PricingPolicy(PricingTypeEnum.PAID, 800_000L);
    }

    @DisplayName("수강생이 결제한 금액과 유료강의 수강료가 다를때 때 예외발생")
    @ParameterizedTest
    @ValueSource(longs = {0, 799_999, 800_001, 400_000})
    void 유료_강의_수강료_불일치(Long fee) throws IllegalArgumentException {
        final Payment payment = new Payment("1", 1L, 1L, fee);

        assertThatIllegalArgumentException().isThrownBy(() -> paidPolicy.canEnrollCheck(payment))
                .withMessage("결제금액이 수강료와 다릅니다.");
    }
}