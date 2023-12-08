package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static nextstep.courses.domain.SessionStatus.OPEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SessionStatusTest {

    @DisplayName("강의가 준비중이거나 종료상태면 신청을 하지 못한다")
    @ParameterizedTest
    @CsvSource({"PREPARING, false", "CLOSED, false"})
    void prepareOrClosed(SessionStatus sessionStatus, boolean expected) {
        assertThat(sessionStatus.canApply()).isEqualTo(expected);
    }

    @DisplayName("강의가 모집중이면 신청이 가능하다")
    @Test
    void openStatus() {
        assertThat(OPEN.canApply()).isTrue();
    }
}