package nextstep.courses.domain;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class SessionPeriodTest {

    public static final LocalDateTime DATE_230601 = LocalDateTime.of(2023, 6, 1, 6, 0, 0);
    public static final LocalDateTime DATE_230630 = LocalDateTime.of(2023, 6, 30, 6, 0, 0);

    @Test
    void testValidatePeriod() {
        assertThatThrownBy(() -> {
            SessionPeriod sessionPeriod = new SessionPeriod(DATE_230630, DATE_230601);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시작일이 종료일 보다 큽니다.");
    }
}