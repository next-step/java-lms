package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionPeriodTest {
    @Test
    void 시작일이_종료일보다_늦으면_예외_발생() {
        LocalDate start = LocalDate.of(2024, 5, 10);
        LocalDate end = LocalDate.of(2024, 5, 1);

        assertThatThrownBy(() -> new SessionPeriod(start, end))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시작일은 종료일보다 이후일 수 없습니다.");
    }
}
