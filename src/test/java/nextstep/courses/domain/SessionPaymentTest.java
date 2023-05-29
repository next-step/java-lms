package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SessionPaymentTest {
    @Test
    void 무료_유료_단순_확인_테스트() {
        Assertions.assertThat(SessionPayment.FREE.name()).isEqualTo("FREE");
        Assertions.assertThat(SessionPayment.PAID.name()).isEqualTo("PAID");
    }
}
