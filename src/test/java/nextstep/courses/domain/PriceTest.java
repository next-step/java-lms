package nextstep.courses.domain;

import nextstep.courses.domain.session.Price;
import nextstep.courses.exception.NegativeOrZeroNumberException;
import nextstep.courses.exception.PaymentAmountNotEqualException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PriceTest {

    @DisplayName("강의 금액은 0 혹은 음수일 수 없다.")
    @ParameterizedTest
    @ValueSource(longs = {-1, 0})
    void price_should_not_be_zero_or_negative(long given) {
        assertThatThrownBy(() -> new Price(given))
                .isInstanceOf(NegativeOrZeroNumberException.class);
    }

    @DisplayName("결제금액과 금액이 동일하지 않는다면 예외가 발생한다.")
    @Test
    void throw_exception_when_payment_amount_is_not_same_as_price() {
        Long givenAmount = 1000L;
        Payment payment = new Payment("1L", 1L, 1L, givenAmount);
        Price price = new Price(999L);

        assertThatThrownBy(() -> price.validatePrice(payment))
                .isInstanceOf(PaymentAmountNotEqualException.class);
    }
}