package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionPeriodTest {
    @Test
    @DisplayName("period 생성")
    void test01() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(1);

        SessionPeriod sessionPeriod = new SessionPeriod(startDate, endDate);

        assertThat(sessionPeriod).isEqualTo(new SessionPeriod(startDate, endDate));
    }

    @Test
    @DisplayName("시작일이 종료일보다 늦는 경우 에러 발생")
    void test02() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.minusDays(1);

        assertThatThrownBy(() -> new SessionPeriod(startDate, endDate)).isInstanceOf(IllegalArgumentException.class);
    }
}
