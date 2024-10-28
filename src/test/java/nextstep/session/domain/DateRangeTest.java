package nextstep.session.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DateRangeTest {

    @DisplayName("강의를 생성할 시 시작일보다 종료일이 빠르면 예외가 발생한다.")
    @Test
    void createSessionNotExistStartDateThrowExceptionTest() {
        //given
        LocalDateTime startDate = LocalDateTime.parse("2023-05-05T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-04-05T00:00:00");

        //when, then
        assertThatThrownBy(() -> new DateRange(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("종료일이 시작일보다 빠릅니다.");
    }
}
