package nextstep.sessions.domain.data.vo;

import nextstep.sessions.domain.data.session.*;
import nextstep.sessions.domain.exception.SessionsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionStateTest {

    @ParameterizedTest
    @EnumSource(value = SessionRunningState.class, names = "RUNNING", mode = EnumSource.Mode.EXCLUDE)
    void 진행중이_아닌_강의(SessionRunningState sessionRunningState) {
        SessionState sessionState = new SessionState(sessionRunningState, SessionRecruitingState.RECRUITING);
        assertThatThrownBy(sessionState::validateState)
            .isInstanceOf(SessionsException.class)
            .hasMessage("진행중이 아닌 강의입니다.");
    }

    @Test
    void 모집중이_아닌_강의() {
        SessionState sessionState = new SessionState(SessionRunningState.RUNNING, SessionRecruitingState.NO_RECRUITING);
        assertThatThrownBy(sessionState::validateState)
            .isInstanceOf(SessionsException.class)
            .hasMessage("모집중이 아닌 강의입니다.");
    }
}
