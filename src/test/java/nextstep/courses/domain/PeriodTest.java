package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PeriodTest {
    @Test
    public void period_StartDateAfterEndDate() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Period(LocalDateTime.now().plusMinutes(1), LocalDateTime.now())
        );
    }
}