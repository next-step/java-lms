package nextstep.courses.domain;

import nextstep.courses.exception.SessionPeriodException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionPeriodTest {

    public static SessionPeriod normalSessionPeriod() {
        return new SessionPeriod(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31)
        );
    }

    @ParameterizedTest
    @MethodSource("providePeriodDate")
    @DisplayName("성공 - 시작일이 종료일 보다 같거나 클 경우 예외가 발생하지 않는다.")
    void range_session_period_date_range(LocalDate startDate, LocalDate endDate) {
        assertThatCode(() -> new SessionPeriod(startDate, endDate))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> providePeriodDate() {
        return Stream.of(
                Arguments.of(
                        LocalDate.of(2023, 5, 5),
                        LocalDate.of(2023, 5, 5)
                ),
                Arguments.of(
                        LocalDate.of(2023, 5, 5),
                        LocalDate.of(2023, 5, 6)
                )
        );
    }

    @Test
    @DisplayName("실패 - 시작일이 종료일 보다 작지 않을 경우 예외가 발생한다.")
    void fail_session_period_date_range() {
        LocalDate startDate = LocalDate.of(2023, 5, 5);
        LocalDate endDate = LocalDate.of(2023, 5, 4);

        assertThatThrownBy(() -> new SessionPeriod(startDate, endDate))
                .isInstanceOf(SessionPeriodException.class)
                .hasMessage("시작일은 종료일 보다 작아야 합니다.");
    }

}
