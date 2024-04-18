package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScheduleTest {

    private static final LocalDate START_DATE = LocalDate.of(2024, 4, 1);
    private static final LocalDate END_DATE = LocalDate.of(2024, 4, 30);
    public static final Schedule SCHEDULE = new Schedule(START_DATE, END_DATE);

    @Test
    @DisplayName("강의 시작일과 종료일을 기준으로 새로운 스케줄을 생성한다.")
    void Schedule() {
        assertThat(new Schedule(START_DATE, END_DATE))
                .isEqualTo(SCHEDULE);
    }

    @Test
    @DisplayName("강의 종료일이 시작일 보다 앞선 경우 예외를 던진다.")
    void EndDateBeforeStartDate_Exception() {
        final LocalDate endDateBeforeStartDate = LocalDate.of(2024, 3, 31);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Schedule(START_DATE, endDateBeforeStartDate));
    }
}
