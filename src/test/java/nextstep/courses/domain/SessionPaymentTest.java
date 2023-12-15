package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionPaymentTest {
    @Test
    @DisplayName("SessionPayment 생성")
    void create() {
        assertThat(new SessionPayment()).isInstanceOf(SessionPayment.class);
    }
}
