package nextstep.sessions.domain.data.type;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PayTypeTest {

    @Test
    void isPay() {
        assertThat(PayType.PAID.isPaid()).isTrue();
    }
}
