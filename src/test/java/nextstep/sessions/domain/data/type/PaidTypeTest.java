package nextstep.sessions.domain.data.type;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaidTypeTest {

    @Test
    void isPaid() {
        assertThat(PaidType.PAID.isPaid()).isTrue();
        assertThat(PaidType.FREE.isPaid()).isFalse();
    }

    @Test
    void isTrue() {
        assertThat(PaidType.PAID.isFree()).isFalse();
        assertThat(PaidType.FREE.isFree()).isTrue();
    }
}
