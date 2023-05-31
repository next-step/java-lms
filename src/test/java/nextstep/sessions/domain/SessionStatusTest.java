package nextstep.sessions.domain;

import nextstep.sessions.CannotRegisterException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SessionStatusTest {

    @ParameterizedTest
    @CsvSource({"PREPARING", "CLOSED"})
    void 강의_상태가_모집중이_아니면_예외를_던진다(SessionStatus status) {
        assertThrows(CannotRegisterException.class, status::validate);
    }

    @Test
    void 강의_상태가_모집중이면_예외를_던지지_않는다() {
        final var status = SessionStatus.OPENING;

        assertDoesNotThrow(status::validate);
    }


}
