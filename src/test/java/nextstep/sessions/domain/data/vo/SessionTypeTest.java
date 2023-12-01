package nextstep.sessions.domain.data.vo;

import nextstep.sessions.domain.data.type.PayType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTypeTest {

    @Test
    void isPaid() {
        SessionType sessionType = new SessionType(PayType.PAY, 1, 1);
        assertThat(sessionType.isPaid()).isTrue();
    }

    @Test
    void isEnoughCapacity() {
        SessionType sessionType = new SessionType(PayType.PAY, 1, 10);
        assertThat(sessionType.isEnoughCapacity(9)).isTrue();
    }

}
