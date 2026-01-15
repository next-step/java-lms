package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionPeriodTest {
    @Test
    public void invalidDateRange() {
        assertThatThrownBy(() -> new SessionPeriod("2026-01-15T14:30:00", "2026-01-01T14:30:00")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void inPeriod() {
        assertThat(new SessionPeriod("2026-01-15T14:30:00", "2026-01-16T14:30:00").canRegister(LocalDateTime.parse("2026-01-15T17:30:00"))).isTrue();
    }

    @Test
    public void afterPeriod() {
        assertThat(new SessionPeriod("2026-01-15T14:30:00", "2026-01-16T14:30:00").canRegister(LocalDateTime.parse("2026-01-17T17:30:00"))).isFalse();
    }

    @Test
    public void beforePeriod() {
        assertThat(new SessionPeriod("2026-01-15T14:30:00", "2026-01-16T14:30:00").canRegister(LocalDateTime.parse("2026-01-01T17:30:00"))).isFalse();
    }
}
