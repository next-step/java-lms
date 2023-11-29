package nextstep.courses.domain.session;

import nextstep.courses.exception.session.InvalidDateRangeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PeriodTest {

    @Test
    @DisplayName("강의 시작일은 종료일 보다 이후일 수는 없다")
    public void validate_session_date(){
        Assertions.assertThatThrownBy(() -> Period.of(LocalDate.of(2023, 11, 29), LocalDate.of(2023, 11, 28)))
                .isInstanceOf(InvalidDateRangeException.class);
    }
}
