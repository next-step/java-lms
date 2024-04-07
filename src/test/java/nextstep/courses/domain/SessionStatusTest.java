package nextstep.courses.domain;

import nextstep.courses.domain.session.SessionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionStatusTest {

    @ParameterizedTest
    @MethodSource("sessionStatusFixture")
    @DisplayName("[성공] 강의 상태에 따른 모집 가능 여부를 확인한다.")
    void 강의_상태_모집_가능_여부(SessionStatus status, boolean expected) {
        assertThat(status.canEnroll()).isEqualTo(expected);
    }

    static Stream<Arguments> sessionStatusFixture() {
        return Stream.of(
                Arguments.of(SessionStatus.PREPARING, true),
                Arguments.of(SessionStatus.IN_PROGRESS, false),
                Arguments.of(SessionStatus.FINISHED, false)
        );
    }
}
