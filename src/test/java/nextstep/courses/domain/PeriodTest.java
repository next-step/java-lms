package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class PeriodTest {

    @Test
    public void 시작일이_종료일보다_뒤일_때_예외_테스트() {
        assertThatThrownBy(() -> new Period(LocalDate.of(2023, 11, 1),
                LocalDate.of(2023, 10, 1)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
