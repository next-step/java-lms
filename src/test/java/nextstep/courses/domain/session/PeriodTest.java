package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class PeriodTest {

    @DisplayName("강의가 종료됐는지 알 수 있다.")
    @Test
    void isEndSession() {
        Period period = new Period(LocalDate.of(2024, 12, 12), LocalDate.of(2025, 1, 1));
        boolean result = period.isEnd(LocalDate.of(2025,1,1));
        assertThat(result).isTrue();
    }
}
