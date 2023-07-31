package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class SessionPeriodTest {

    public static SessionPeriod SP1 = SessionPeriod.between(LocalDateTime.MIN, LocalDateTime.MAX);

    @Test
    void 시작일은_종료일보다_빨라야한다() {
        assertThatIllegalArgumentException().isThrownBy(()->
                SessionPeriod.between(LocalDateTime.MAX, LocalDateTime.MIN));
    }
}
