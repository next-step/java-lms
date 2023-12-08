package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SessionPeriodTest {
    private LocalDateTime now = LocalDateTime.now();

    @Test
    void sessionPeriodTest() {
        final LocalDateTime nextDay = now.plusDays(1);
        final SessionPeriod sessionPeriod = new SessionPeriod(now, nextDay);
        assertThat(sessionPeriod).isEqualTo(new SessionPeriod(now, nextDay));
    }

    @Test
    void validateSessionPeriodTest() {
        assertThatThrownBy(() -> {
            new SessionPeriod(now, now.minusDays(1));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
