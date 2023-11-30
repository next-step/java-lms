package nextstep.courses.domain;

import nextstep.courses.exception.ProgressPeriodException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ProgressPeriodTest {

    @Test
    @DisplayName("종료일이 시작일보다 이전이면 에러를 던진다.")
    void 생성_날짜차이_에러() {
        LocalDate startDate = LocalDate.of(2023, 11, 30);
        LocalDate endDate = LocalDate.of(2023, 11, 29);

        Assertions.assertThatThrownBy(() -> new ProgressPeriod(startDate, endDate))
                .isInstanceOf(ProgressPeriodException.class);
    }
}
