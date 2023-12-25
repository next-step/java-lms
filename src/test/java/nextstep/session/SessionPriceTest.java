package nextstep.session;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.session.domain.SessionPrice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionPriceTest {

    @Test
    @DisplayName("수강료가 0원인 경우에는 무료강의")
    void sessionPriceZeroIsFreeTest() {
        SessionPrice freeSessionPrice = new SessionPrice(0);
        assertThat(freeSessionPrice.isFree()).isTrue();
    }

    @Test
    @DisplayName("수강료가 존재하는 경우에는 유료강의")
    void sessionPricePaidIsNotFreeTest() {
        SessionPrice freeSessionPrice = new SessionPrice(100);
        assertThat(freeSessionPrice.isFree()).isFalse();
    }
}
