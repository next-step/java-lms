package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SessionFeeTest {
    @Test
    void negativeFeeTest() {
        assertThatThrownBy(() -> {
            new SessionFee(-1L);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void freeFeeTest() {
        final SessionFee sessionFee = new SessionFee(0L);
        assertThat(sessionFee.isFree()).isTrue();
    }

    @Test
    void differentFeeTest() {
        final SessionFee sessionFee = new SessionFee(1000L);
        final Payment payment = new Payment("id", 1L, 1L, 1500L);
        assertThat(sessionFee.isNotEqualTo(payment)).isTrue();
    }
}
