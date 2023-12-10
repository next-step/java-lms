package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionPeriodTest {
    @Test
    @DisplayName("강의 시작일이 종요일보다 늦는 경우 예외가 발생한다.")
    void date_exception() {
        LocalDate startDate = LocalDate.now().plusDays(1L);
        LocalDate endDate = LocalDate.now();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SessionPeriod(startDate, endDate));
    }
}