package nextstep.courses.domain;

import nextstep.courses.NotIncludePeriodException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static nextstep.courses.domain.SessionPeriodFixture.과거기간;
import static nextstep.courses.domain.SessionPeriodFixture.미래기간;
import static nextstep.courses.domain.SessionPeriodFixture.현재기간_포함;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionPeriodTest {

    private LocalDate now = LocalDate.now();

    @Test
    @DisplayName("수강 가능 기간에 신청을 한 경우")
    void testIncludeCurrentDate_정상_범위() {
        SessionPeriod sessionPeriod = 현재기간_포함();
        assertThatNoException()
                .isThrownBy(() -> sessionPeriod.includeCurrentDate(now));
    }

    @Test
    @DisplayName("수강 가능 기간 지난 후 신청을 한 경우")
    void testIncludeCurrentDate_과거_범위() {
        SessionPeriod sessionPeriod = 과거기간();
        assertThatThrownBy(() -> sessionPeriod.includeCurrentDate(now))
                .isInstanceOf(NotIncludePeriodException.class)
                .hasMessage(NotIncludePeriodException.MESSAGE);
    }

    @Test
    @DisplayName("수강 가능 기간 이전에 신청을 한 경우")
    void testIncludeCurrentDate_미래_범위() {
        SessionPeriod sessionPeriod = 미래기간();
        assertThatThrownBy(() -> sessionPeriod.includeCurrentDate(now))
                .isInstanceOf(NotIncludePeriodException.class)
                .hasMessage(NotIncludePeriodException.MESSAGE);
    }
}
