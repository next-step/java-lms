package nextstep.lms.domain;

import nextstep.lms.enums.PricingTypeEnum;
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
        this.freePolicy = new PricingPolicy(PricingTypeEnum.FREE, 0);
        this.paidPolicy = new PricingPolicy(PricingTypeEnum.PAID, 800000);
    }

    @DisplayName("무료강의는 수강료와 상관없이 등록 가능")
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 3000, 800000})
    void 무료_강의_조건없음(int fee) {
        assertThat(freePolicy.canEnroll(fee)).isTrue();
    }

    @DisplayName("유료강의는 결제한 금액과 수강료가 일치할 때 수강 신청이 가능")
    @Test
    void 유료_강의_수강료_일치() throws IllegalArgumentException {
        assertThat(paidPolicy.canEnroll(800000)).isTrue();
    }

    @DisplayName("수강생이 결제한 금액과 수강료가 다를때 때 예외발생")
    @ParameterizedTest
    @ValueSource(ints = {0, 799999, 800001, 400000})
    void 유료_강의_수강료_불일치(int fee) throws IllegalArgumentException {
        assertThatThrownBy(() -> paidPolicy.canEnroll(fee))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제금액이 수강료와 다릅니다.");
    }
}