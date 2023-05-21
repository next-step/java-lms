package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SessionDateTest {
    @Test
    @DisplayName("수강 시작일이 수강 종료일 이후일 경우, IllegalArgumentException 예외 발생")
    void when_start_session_date_is_after_end_session_date_then_throw_IllegalArgumentException() {
        LocalDate startDate = LocalDate.of(2023, 5, 22);
        LocalDate endDate = LocalDate.of(2023, 5, 21);

        Assertions.assertThatThrownBy(() -> new SessionDate(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강 시작일(" + startDate + ")은 수강 종료일(" + endDate + ") 이후일 수 없습니다.");
    }
}
