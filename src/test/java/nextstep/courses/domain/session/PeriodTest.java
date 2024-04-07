package nextstep.courses.domain.session;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {

    @DisplayName("기간이 시작됐는지 확인할 수 있다.")
    @Test
    void isStartSession() {
        Period period = new Period(LocalDate.of(2024, 12, 12), LocalDate.of(2025, 1, 1));
        boolean result = period.isStart(LocalDate.of(2024,12,12));
        assertThat(result).isTrue();
    }
}
