package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AmountTest {
    @Test
    void 금액이_음수일떄() {
        assertThatThrownBy(() -> {
            new Amount(-1L);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 금액이_0원일때() {
        final Amount amount = new Amount(0L);
        assertThat(amount.isFree()).isTrue();
    }

    @Test
    void 금액이_다를떄() {
        final Amount amount = new Amount(100L);
        final Payment payment = new Payment("qwe5507", 1L, 1L, 10L);
        assertThat(amount.isNotSame(payment)).isTrue();
    }
}