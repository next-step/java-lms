package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionPeriodTest {

    @Test
    @DisplayName("강의 시작일은 종료일보다 이전이어야 한다.")
    void createSessionWithInvalidStartDate() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.plusMonths(1);
        assertThatThrownBy(() -> new SessionPeriod(startDate, endDate)).isInstanceOf(IllegalArgumentException.class);
    }


}
