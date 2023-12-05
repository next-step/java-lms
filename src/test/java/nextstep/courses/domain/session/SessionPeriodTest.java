package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionPeriodTest {
    @Test
    void create() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime nextDay = LocalDateTime.now().plusDays(1);
        final SessionPeriod sessionPeriod = new SessionPeriod(now, nextDay);

        assertThat(sessionPeriod).isEqualTo(new SessionPeriod(now, nextDay));
    }

    @DisplayName("종료일은 시작일보다 빠를 수 없다.")
    @Test
    void invalidDate() {
        assertThatThrownBy(() -> {
            new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().minusDays(1));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}