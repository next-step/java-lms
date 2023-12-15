package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionPaymentTypeTest {
    @Test
    @DisplayName("SessionPaymentType 확인")
    void create() {
        assertThat(SessionPaymentType.PAID).isInstanceOf(SessionPaymentType.class);
    }
}
