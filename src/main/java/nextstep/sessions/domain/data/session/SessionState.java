package nextstep.sessions.domain.data.session;

import nextstep.sessions.domain.exception.CannotEnrollRegistrationException;

public class SessionState {

    private final SessionRecruitingState sessionRecruitingState;
    private final SessionRunningState sessionRunningState;

    public SessionState(SessionRunningState sessionRunningState, SessionRecruitingState sessionRecruitingState) {
        this.sessionRunningState = sessionRunningState;
        this.sessionRecruitingState = sessionRecruitingState;
    }

    public void validateState() {
        if (!sessionRunningState.isRunning()) {
            throw new CannotEnrollRegistrationException("진행중이 아닌 강의입니다.");
        }
        if (!sessionRecruitingState.isRecruiting()) {
            throw new CannotEnrollRegistrationException("모집중이 아닌 강의입니다.");
        }
    }

    public String sessionRecruitingStateName() {
        return sessionRecruitingState.name();
    }

    public String sessionRunningStateName() {
        return sessionRunningState.name();
    }

}
