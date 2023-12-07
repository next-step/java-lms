package nextstep.lms.domain;

import nextstep.lms.enums.PricingTypeEnum;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PricingPolicyTest {
    private PricingPolicy freePolicy;
    private PricingPolicy paidPolicy;

    @BeforeEach
    void setting() {
        this.freePolicy = new PricingPolicy(PricingTypeEnum.FREE, 0L);
        this.paidPolicy = new PricingPolicy(PricingTypeEnum.PAID, 800_000L);
    }

    @DisplayName("무료강의는 수강료와 상관없이 등록 가능")
    @ParameterizedTest
    @ValueSource(longs = {0L, 1_000L, 3_000L, 800_000L})
    void 무료_강의_조건없음(Long fee) {
        assertThat(freePolicy.canEnroll(new Payment("1", 1L, 1L, fee))).isTrue();
    }

    @DisplayName("유료강의는 결제한 금액과 수강료가 일치할 때 수강 신청이 가능")
    @Test
    void 유료_강의_수강료_일치() throws IllegalArgumentException {

        assertThat(paidPolicy.canEnroll(new Payment("1", 1L, 1L, 800_000L))).isTrue();
    }

    @DisplayName("수강생이 결제한 금액과 수강료가 다를때 때 예외발생")
    @ParameterizedTest
    @ValueSource(longs = {0, 799_999, 800_001, 400_000})
    void 유료_강의_수강료_불일치(Long fee) throws IllegalArgumentException {
        assertThatThrownBy(() -> paidPolicy.canEnroll(new Payment("1", 1L, 1L, fee)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제금액이 수강료와 다릅니다.");
    }
}