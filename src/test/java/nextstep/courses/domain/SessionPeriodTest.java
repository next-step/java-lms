package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class SessionPeriodTest {
    @Test
    @DisplayName("강의 종료일이 시작일보다 빠르면 예외가 던져진다")
    void startedAt_endedAt_exception() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new SessionPeriod(LocalDate.of(2023, 1, 1), LocalDate.of(2022, 12, 31));
        });
    }
    @Test
    @DisplayName("강의는 시작일과 종료일을 가진다")
    void create() {
        LocalDate startedAt = LocalDate.of(2022, 12, 31);
        LocalDate endedAt = LocalDate.of(2023, 1, 1);
        SessionPeriod sessionPeriod = new SessionPeriod(startedAt, endedAt);
        assertThat(sessionPeriod).isEqualTo(new SessionPeriod(startedAt, endedAt));
    }
}
