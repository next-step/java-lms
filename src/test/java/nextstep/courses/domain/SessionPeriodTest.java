package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class SessionPeriodTest {
    static final LocalDate YESTERDAY = LocalDate.now().minusDays(1);
    static final LocalDate TODAY = LocalDate.now();
    static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    static final SessionPeriod SESSION_PERIOD = new SessionPeriod(TODAY, TOMORROW);

    @Test
    public void create() throws Exception {
        assertThat(SESSION_PERIOD)
                .isNotNull()
                .isInstanceOf(SessionPeriod.class);
    }

    @Test
    public void 기간_유효성_검증() throws Exception {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SessionPeriod(TODAY, YESTERDAY));
    }

}
