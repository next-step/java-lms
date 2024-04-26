package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import nextstep.courses.domain.session.period.Period;
import nextstep.courses.error.exception.EndDateBeforeStartDateException;
import nextstep.courses.error.exception.NotExistTimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PeriodTest {

    @ParameterizedTest
    @CsvSource(value = { "null : '2024-04-22T18:00:00'", "'2024-04-20T09:00:00' : null" }, nullValues = "null", delimiter = ':')
    void 시간값은_null일_경우_예외가_발생한다(String startDate, String endDate) {
        LocalDateTime parsedStartDate = startDate != null ? LocalDateTime.parse(startDate) : null;
        LocalDateTime parsedEndDate = endDate != null ? LocalDateTime.parse(endDate) : null;

        assertThatThrownBy(() -> new Period(parsedStartDate, parsedEndDate))
            .isInstanceOf(NotExistTimeException.class);
    }

    @Test
    void 종료일이_시작일보다_이전_시간이라면_예외가_발생한다() {
        LocalDateTime startDate = LocalDateTime.of(2024, 4, 22, 18, 0); // 시작일
        LocalDateTime endDate = LocalDateTime.of(2024, 4, 20, 9, 0); // 종료일

        assertThatThrownBy(() -> new Period(startDate, endDate))
            .isInstanceOf(EndDateBeforeStartDateException.class)
            .hasMessage("등록일이 시작일보다 이전입니다, 입력값: 2024-04-22T18:00, 2024-04-20T09:00");
    }

    @Test
    void 시작일과_종료일은_같을_수_있다() {
        LocalDateTime startDate = LocalDateTime.of(2024, 4, 22, 18, 0); // 시작일
        LocalDateTime endDate = LocalDateTime.of(2024, 4, 22, 18, 0); // 종료일

        Period period = new Period(startDate, endDate);

        assertEquals(startDate, period.getStartDate());
        assertEquals(endDate, period.getEndDate());
    }
}
