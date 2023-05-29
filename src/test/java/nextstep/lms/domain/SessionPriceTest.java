package nextstep.lms.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class SessionPriceTest {

    @Test
    void 무료() {
        SessionPrice sessionPrice_0 = new SessionPrice(0);
        SessionPrice sessionPrice_null = new SessionPrice(null);
        assertAll(
                () -> assertThat(sessionPrice_0.isFree()).isTrue(),
                () -> assertThat(sessionPrice_null.isFree()).isTrue()
        );
    }

    @Test
    void 유료() {
        SessionPrice sessionPrice = new SessionPrice(3000);
        assertThat(sessionPrice.isPaid()).isTrue();
    }

    @Test
    void 수강료_음수_설정시() {
        assertThatThrownBy(() -> new SessionPrice(-1000)).isInstanceOf(IllegalArgumentException.class);
    }
}
