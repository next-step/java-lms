package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AmountTest {
    @Test
    void 금액이_음수일떄() {
        assertThatThrownBy(() -> {
            new Amount(-1L);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}