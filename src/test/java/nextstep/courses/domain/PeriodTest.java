package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PeriodTest {

    @Test
    void 종료일은_시작일을_앞설수없다() {
        assertThatThrownBy(() -> {
            new Period(LocalDate.now(), LocalDate.now().minusDays(1));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
