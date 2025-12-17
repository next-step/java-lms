package nextstep.courses.cohort.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class PeriodTest {

    @Test
    void 수강신청_시작일이_NULL이면_예외처리_할_수_있다() {
        assertThatThrownBy(
                () -> new Period(null, LocalDateTime.now())
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 수강신청_종료일이_NULL이면_예외처리_할_수_있다() {
        assertThatThrownBy(
                () -> new Period(LocalDateTime.now(), null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 수강신청_시작일이_종료일보다_미래이면_예외처리_할_수_있다() {
        LocalDateTime endDate = LocalDateTime.of(2025, 1, 1, 0, 0, 0);
        LocalDateTime startDate = endDate.plusSeconds(1);

        assertThatThrownBy(
                () -> new Period(startDate, endDate)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}