package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class PeriodTest {

    static final LocalDate START_DATE = LocalDate.of(2025, 11, 3);
    static final LocalDate END_DATE = LocalDate.of(2025, 12, 18);
    public static final Period P1 = new Period(START_DATE, END_DATE);

    @Test
    void startDateMustBeBeforeEndDate() {
        assertThatThrownBy(() -> new Period(END_DATE, START_DATE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시작일이 종료일보다");
    }

}