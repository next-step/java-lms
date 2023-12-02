package nextstep.courses.domain;

import nextstep.courses.exception.SessionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static nextstep.courses.domain.SessionImageTest.*;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    public static final SessionPeriod P1 = new SessionPeriod(
            LocalDate.of(2023, 1, 1),
            LocalDate.of(2023, 12, 31)
    );

    @ParameterizedTest
    @MethodSource("provideSessionStateExcludeOpen")
    @DisplayName("실패 - 수강 신청시 모집중이 아닌 경우 수강 신청을 할 수 없다.")
    void fail_session_register_not_open(SessionState sessionState) {
        Session session = new Session(
                normalSessionImage(),
                P1,
                sessionState,
                SessionType.FREE
        );
        assertThatThrownBy(session::register)
                .isInstanceOf(SessionException.class)
                .hasMessage("현재 강의가 모집중이지 않아 수강 신청을 할 수가 없습니다.");
    }

    private static Stream<Arguments> provideSessionStateExcludeOpen() {
        return Stream.of(
                Arguments.of(SessionState.PREPARE),
                Arguments.of(SessionState.CLOSE)
        );
    }

    @Test
    @DisplayName("성공 - 수강 신청시 모집중인 경우 수강 신청을 할 수 있다.")
    void success_session_register_open() {
        Session session = new Session(
                normalSessionImage(),
                P1,
                SessionState.OPEN,
                SessionType.FREE
        );
        assertThatCode(session::register)
                .doesNotThrowAnyException();
    }

}
