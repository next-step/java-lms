package nextstep.courses.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionPeriodTest {

    public static final SessionPeriod PERIOD = new SessionPeriod(
            LocalDate.of(2024, 01 , 01),
            LocalDate.of(2024, 12, 31));

    @Test
    @DisplayName("강의 기간 실패 테스트 - 시작일이 종료일보다 뒤인 경우")
    void sessionPeriodFailForStartDateIsAfterEndDateTest() {
        assertThatThrownBy(() -> {
                    new SessionPeriod(
                            LocalDate.of(2025, 01 , 01),
                            LocalDate.of(2024, 12, 31));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작일은 종료일보다 뒤이어야 합니다.");
    }

    @Test
    @DisplayName("강의 기간 실패 테스트 - 시작일이 종료일과 같은 경우")
    void sessionPeriodFailForStartDateEqualsEndDateTest() {
        assertThatThrownBy(() -> {
            new SessionPeriod(
                    LocalDate.of(2024, 01 , 01),
                    LocalDate.of(2024, 01, 01));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작일과 종료일은 같을 수 없습니다.");
    }
}
