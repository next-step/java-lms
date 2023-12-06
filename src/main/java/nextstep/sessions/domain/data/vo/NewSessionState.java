package nextstep.sessions.domain.data.vo;

import nextstep.sessions.domain.data.type.SessionRecruitingState;
import nextstep.sessions.domain.data.type.SessionRunningState;
import nextstep.sessions.domain.exception.SessionsException;

public class NewSessionState {

    private final SessionRecruitingState sessionRecruitingState;
    private final SessionRunningState sessionRunningState;

    public NewSessionState(SessionRunningState sessionRunningState, SessionRecruitingState sessionRecruitingState) {
        this.sessionRunningState = sessionRunningState;
        this.sessionRecruitingState = sessionRecruitingState;
    }

    public void validateState() {
        if (!sessionRunningState.isRunning()) {
            throw new SessionsException("진행중이 아닌 강의입니다.");
        }
        if (!sessionRecruitingState.isRecruiting()) {
            throw new SessionsException("모집중이 아닌 강의입니다.");
        }
    }

    public SessionRecruitingState sessionRecruitingState() {
        return sessionRecruitingState;
    }

    public SessionRunningState sessionRunningState() {
        return sessionRunningState;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        NewSessionState that = (NewSessionState) object;

        if (sessionRecruitingState != that.sessionRecruitingState) {
            return false;
        }
        return sessionRunningState == that.sessionRunningState;
    }

    @Override
    public int hashCode() {
        int result = sessionRecruitingState != null ? sessionRecruitingState.hashCode() : 0;
        result = 31 * result + (sessionRunningState != null ? sessionRunningState.hashCode() : 0);
        return result;
    }
}
