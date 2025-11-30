package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionPeriodTest {

    @Test
    public void 정상적인_기간_생성() {
        LocalDate startDate = LocalDate.of(2025, 11, 3);
        LocalDate endDate = LocalDate.of(2025, 12, 18);

        SessionPeriod period = new SessionPeriod(startDate, endDate);

        assertThat(period).isNotNull();
    }

    @Test
    public void 종료일이_시작일보다_이전이면_예외() {
        LocalDate startDate = LocalDate.of(2026, 11, 3);
        LocalDate endDate = LocalDate.of(2025, 12, 18);

        assertThatThrownBy(() -> {
            new SessionPeriod(startDate, endDate);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("종료일은 시작일보다 이후여야 한다");
    }

}
