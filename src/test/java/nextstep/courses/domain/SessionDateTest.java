package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionDateTest {
    @Test
    void 시작일_종료일() {
        LocalDate startDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 30);

        SessionDate sessionDate = new SessionDate(startDate, endDate);

        assertThat(sessionDate.getStartDate()).isNotNull();
        assertThat(sessionDate.getEndDate()).isNotNull();
    }

    @Test
    void 종료일보다_시작일이_먼저() {
        LocalDate endDate = LocalDate.of(2023, 5, 1);
        LocalDate startDate = LocalDate.of(2023, 5, 30);

        assertThatThrownBy(() -> new SessionDate(startDate, endDate))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("날짜가 올바르지 않습니다.");
    }
}
