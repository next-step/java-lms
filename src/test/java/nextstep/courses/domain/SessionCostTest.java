package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SessionCostTest {

    @Test
    void paymentCheck() {
        SessionCost sessionCost = new SessionCost(1430);
        Payment payment = new Payment("id", 2L, 23L, 1430L);
        assertThat(sessionCost.paymentCheck(payment)).isEqualTo(true);
    }
}