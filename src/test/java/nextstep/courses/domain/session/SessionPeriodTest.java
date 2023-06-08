package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@DisplayName("SessionPeriod 테스트")
class SessionPeriodTest {

    @Test
    @DisplayName("시작일 보다 종료일이 더 빠를경우 예외가 발생하여야 한다")
    void start_date_is_after_end_date() {
        assertThatThrownBy(() ->
                new SessionPeriod(LocalDate.now().plusDays(30),LocalDate.now())
        ).isInstanceOf(IllegalArgumentException.class);
    }


}