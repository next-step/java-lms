package nextstep.sessions.domain.data.vo;

import nextstep.sessions.domain.data.type.SessionRecruitingState;
import nextstep.sessions.domain.data.type.SessionRunningState;
import nextstep.sessions.domain.exception.SessionsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NewSessionStateTest {

    @ParameterizedTest
    @EnumSource(value = SessionRunningState.class, names = "RUNNING", mode = EnumSource.Mode.EXCLUDE)
    void 진행중이_아닌_강의(SessionRunningState sessionRunningState) {
        NewSessionState newSessionState = new NewSessionState(sessionRunningState, SessionRecruitingState.RECRUITING);
        assertThatThrownBy(newSessionState::validateState)
            .isInstanceOf(SessionsException.class)
            .hasMessage("진행중이 아닌 강의입니다.");
    }

    @Test
    void 모집중이_아닌_강의() {
        NewSessionState newSessionState = new NewSessionState(SessionRunningState.RUNNING, SessionRecruitingState.NO_RECRUITING);
        assertThatThrownBy(newSessionState::validateState)
            .isInstanceOf(SessionsException.class)
            .hasMessage("모집중이 아닌 강의입니다.");
    }
}
