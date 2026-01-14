package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionPeriodTest {
    @Test
    public void invalidDateRange() {
        assertThatThrownBy(()->new SessionPeriod("2026-01-15T14:30:00", "2026-01-01T14:30:00")).isInstanceOf(IllegalArgumentException.class);
    }
}
