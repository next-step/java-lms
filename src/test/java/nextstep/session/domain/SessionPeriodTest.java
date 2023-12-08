package nextstep.session.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class SessionPeriodTest {

    @ParameterizedTest
    @DisplayName("강의가 시작했는지 확인한다.")
    @MethodSource("parametersProvider")
    void check_session_is_start(LocalDate given, boolean expected) {
        // given
        SessionPeriod sessionPeriod = new SessionPeriod(
                LocalDate.of(2023, 12, 8),
                LocalDate.of(2023, 12, 25)
        );

        // when
        boolean result = sessionPeriod.isStart(() -> given);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> parametersProvider() {
        return Stream.of(
                arguments(LocalDate.of(2023,12,7), false),
                arguments(LocalDate.of(2023,12,8), true)
        );
    }
}
