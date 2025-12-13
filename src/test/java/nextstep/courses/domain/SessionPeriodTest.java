package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class SessionPeriodTest {

    @Test
    void 끝나는_시간이_시작_시간_보다_빠르면_오류() {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().minusDays(1);

        Assertions.assertThatThrownBy(
                () -> new SessionPeriod(startTime, endTime)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("종료 시간은 시작 시간 이후여야 합니다.");
    }
}
