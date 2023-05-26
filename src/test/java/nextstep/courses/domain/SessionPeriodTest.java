package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SessionPeriodTest {
    @Test
    public void 강의_종료일은_시작일_보다_뒤가_아니면_예외가_난다() throws Exception {
        LocalDate from = LocalDate.of(2023, 5, 25);
        LocalDate to = LocalDate.of(2023, 5, 24);

        Assertions.assertThatThrownBy(() -> {
            SessionPeriod period = new SessionPeriod(from, to);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}