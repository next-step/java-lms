package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionFeeTest {

    @ParameterizedTest
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    @CsvSource(value = {"200:true", "201:false", "201:false"}, delimiter = ':') // 우선 금액이 더 커도 false 리턴하도록
    void paid_lecture_can_enroll_when_match_fee(Long fee, boolean result) {
        boolean canPurchase = new SessionFee(200L).canPurchase(new Payment("1234", 1L, 1L, fee));
        assertThat(canPurchase).isEqualTo(result);
    }

    @ParameterizedTest
    @DisplayName("수강료가 무료면 무료 강의")
    @CsvSource(value = {"0:true", "1:false"}, delimiter = ':')
    void is_free(Long fee, boolean result) {
        assertThat(new SessionFee(fee).isFree()).isEqualTo(result);
    }

    @Test
    @DisplayName("수강료는 0보다는 이상")
    void if_negative_number() {
        assertThatThrownBy(() -> {
            new SessionFee(-1L);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
