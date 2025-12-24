package nextstep.courses.cohort.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class PeriodTest {

    @Test
    void 시작일이_NULL이면_예외처리_할_수_있다() {
        assertThatThrownBy(
                () -> new Period(null, LocalDateTime.now())
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 종료일이_NULL이면_예외처리_할_수_있다() {
        assertThatThrownBy(
                () -> new Period(LocalDateTime.now(), null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 시작일이_종료일보다_미래이면_예외처리_할_수_있다() {
        LocalDateTime endDate = LocalDateTime.of(2025, 1, 1, 0, 0, 0);
        LocalDateTime startDate = endDate.plusSeconds(1);

        assertThatThrownBy(
                () -> new Period(startDate, endDate)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 특정시점이_기간에_포함되는지_확인_할_수_있다() {
        LocalDateTime startDate = LocalDateTime.of(2025, 1, 1, 0, 29, 28);
        LocalDateTime endDate = LocalDateTime.of(2025, 1, 1, 0, 29, 30);
        LocalDateTime target = LocalDateTime.of(2025, 1, 1, 0, 29, 29);

        assertThat(
                new Period(startDate, endDate).isPeriodIn(target)
        ).isTrue();
    }

    @Test
    void 특정시점이_종료일보다_미래인지_확인_할_수_있다() {
        LocalDateTime startDate = LocalDateTime.of(2025, 1, 1, 0, 29, 28);
        LocalDateTime endDate = LocalDateTime.of(2025, 1, 1, 0, 29, 30);
        LocalDateTime target = LocalDateTime.of(2025, 1, 1, 0, 29, 31);

        assertThat(
                new Period(startDate, endDate).isOverEndDate(target)
        ).isTrue();
    }

    @Test
    void 특정시점이_시작일보다_과거인지_확인_할_수_있다() {
        LocalDateTime startDate = LocalDateTime.of(2025, 1, 1, 0, 29, 28);
        LocalDateTime endDate = LocalDateTime.of(2025, 1, 1, 0, 29, 30);
        LocalDateTime target = LocalDateTime.of(2025, 1, 1, 0, 29, 27);

        assertThat(
                new Period(startDate, endDate).isBeforeStartDate(target)
        ).isTrue();
    }

}