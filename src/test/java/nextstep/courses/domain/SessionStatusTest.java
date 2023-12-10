package nextstep.courses.domain;

import nextstep.courses.domain.session.SessionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static nextstep.courses.domain.session.SessionStatus.OPEN;
import static org.assertj.core.api.Assertions.assertThat;

class SessionStatusTest {

    @DisplayName("강의가 준비중이거나 종료상태면 false를 반환한다")
    @ParameterizedTest
    @CsvSource({"PREPARING, false", "CLOSED, false"})
    void prepareOrClosed(SessionStatus sessionStatus, boolean expected) {
        assertThat(sessionStatus.canApply()).isEqualTo(expected);
    }

    @DisplayName("강의가 모집중이면 true를 반환한다")
    @Test
    void openStatus() {
        assertThat(OPEN.canApply()).isTrue();
    }
}