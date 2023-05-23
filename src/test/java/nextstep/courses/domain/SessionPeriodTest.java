package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionPeriodTest {
    @Test
    void 종료일이_시작일보다_앞서면_오류를_throw() {
        LocalDateTime fromDate = LocalDateTime.of(2023, 5, 20, 0, 0, 0);
        LocalDateTime toDate = LocalDateTime.of(2023, 5, 19, 0, 0, 0);

        assertThatThrownBy(() -> new SessionPeriod(fromDate, toDate)).isInstanceOf(IllegalArgumentException.class);
    }
}