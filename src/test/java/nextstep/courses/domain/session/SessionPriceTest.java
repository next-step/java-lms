package nextstep.courses.domain.session;

import nextstep.courses.domain.session.info.detail.SessionPrice;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionPriceTest {
    @Test
    @DisplayName("유료 강의의 수강료가 0이면 예외가 발생한다")
    void validatePrice() {
        assertThatThrownBy(() -> new SessionPrice(SessionType.PAID, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료 강의는 수강료가 0보다 커야 합니다.");
    }

    @Test
    @DisplayName("무료 강의는 수강료가 0이어도 된다")
    void createFreeSessionPrice() {
        // given
        SessionPrice price = new SessionPrice(SessionType.FREE, 0);

        // when & then
        assertThat(price.isPaid()).isFalse();
        assertThat(price.getPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("유료 강의는 수강료가 0보다 커야 한다")
    void createPaidSessionPrice() {
        // given
        SessionPrice price = new SessionPrice(SessionType.PAID, 100000);

        // when & then
        assertThat(price.isPaid()).isTrue();
        assertThat(price.getPrice()).isEqualTo(100000);
    }

    @Test
    @DisplayName("유료 강의의 결제 금액이 수강료와 다르면 예외가 발생한다")
    void validatePayment() {
        // given
        SessionPrice price = new SessionPrice(SessionType.PAID, 100000);
        Payment payment = new Payment("payment-1", 1L, 1L, 90000L);

        // when & then
        assertThatThrownBy(() -> price.validatePayment(payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제 금액이 수강료와 일치하지 않습니다.");
    }

    @Test
    @DisplayName("무료 강의는 결제 금액 검증을 하지 않는다")
    void validateFreeSessionPayment() {
        // given
        SessionPrice price = new SessionPrice(SessionType.FREE, 0);
        Payment payment = new Payment("payment-1", 1L, 1L, 100000L);

        // when & then
        price.validatePayment(payment);
    }
} 