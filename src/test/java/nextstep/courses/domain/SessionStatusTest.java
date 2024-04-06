package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionStatusTest {

    @DisplayName("강의 상태가 모집중인지 확인한다.")
    @ParameterizedTest
    @MethodSource("checkStatus")
    void test01(SessionStatus status, boolean expected) {
        assertThat(SessionStatus.isRecruiting(status)).isEqualTo(expected);
    }

    public static Stream<Arguments> checkStatus() {
        return Stream.of(
                Arguments.of(SessionStatus.PREPARING, false),
                Arguments.of(SessionStatus.RECRUITING, true),
                Arguments.of(SessionStatus.FINISHED, false)
        );
    }
}
