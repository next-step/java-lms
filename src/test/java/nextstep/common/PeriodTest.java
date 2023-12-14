package nextstep.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class PeriodTest {
    public static final Period NOV = new Period(LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 30));
    public static final Period DEC = new Period(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31));

    @DisplayName("시작일과 종료일을 전달하면 SessionDate 객체를 생성한다.")
    @Test
    void periodTest() {
        assertThat(new Period(LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 15))).isInstanceOf(Period.class);
    }

    @DisplayName("종료일이 시작일 전이면 IllegalStateException을 던진다.")
    @Test
    void periodExceptionTest() {
        assertThatThrownBy(() -> new Period(LocalDate.of(2023, 11, 2), LocalDate.of(2023, 11, 1)))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("오늘이 시작일 이루이면서 종료일 이전이면 참을 반환한다.")
    @Test
    void isInProgressTrueTest() {
        Period period = new Period(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 15));

        assertThat(period.isInProgress()).isTrue();
    }

    @DisplayName("오늘이 시작일 이전이거나 종료일 이후라면 거짓을 반환한다.")
    @Test
    void isInProgressFalseTest() {
        Period period1 = new Period(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 2));
        Period period2 = new Period(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2));

        assertThat(period1.isInProgress()).isFalse();
        assertThat(period2.isInProgress()).isFalse();
    }
}
