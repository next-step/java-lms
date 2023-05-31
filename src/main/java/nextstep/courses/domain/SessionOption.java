package nextstep.courses.domain;

import java.util.Objects;

public class SessionOption {

    private SessionStatus sessionStatus = SessionStatus.READY;
    private SessionType sessionType = SessionType.FREE;
    private SessionEnrollment sessionEnrollment = SessionEnrollment.ENROLLMENT;

    public SessionOption() {

    }

    public SessionOption(SessionType sessionType, SessionStatus sessionStatus, SessionEnrollment sessionEnrollment) {
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.sessionEnrollment = sessionEnrollment;
    }

    public SessionOption(SessionEnrollment sessionEnrollment) {
        this.sessionEnrollment = sessionEnrollment;
    }

    public void validateStatusOfSession() {
        if(SessionStatus.isEnd(sessionStatus)){
            throw new IllegalStateException("현재 강의는 종료 되었습니다.");
        }

        if (SessionEnrollment.isNotEnrollment(sessionEnrollment)) {
            throw new IllegalStateException("현재 강의 모집 중이 아닙니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionOption that = (SessionOption) o;
        return sessionStatus == that.sessionStatus && sessionType == that.sessionType && sessionEnrollment == that.sessionEnrollment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionStatus, sessionType, sessionEnrollment);
    }

}
