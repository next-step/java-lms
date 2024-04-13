package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScheduleTest {

    @Test
    @DisplayName("강의 시작일과 종료일을 기준으로 새로운 스케줄을 생성한다.")
    void Schedule() {
        final LocalDate startDate = LocalDate.of(2024, 4, 1);
        final LocalDate endDate = LocalDate.of(2024, 4, 30);

        assertThat(new Schedule(startDate, endDate))
                .isEqualTo(new Schedule(startDate, endDate));
    }

    @Test
    @DisplayName("강의 종료일이 시작일 보다 앞선 경우 예외를 던진다.")
    void EndDateBeforeStartDate_Exception() {
        final LocalDate startDate = LocalDate.of(2024, 4, 1);
        final LocalDate endDate = LocalDate.of(2024, 3, 31);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Schedule(startDate, endDate));
    }
}
