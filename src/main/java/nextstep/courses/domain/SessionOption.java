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

    public SessionOption(SessionType sessionType, SessionStatus sessionStatus) {
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
    }
    public void validateStatusOfSession() {
        if (SessionEnrollment.isNotEnrollment(sessionEnrollment)) {
            throw new IllegalStateException("현재 강의 모집 중이 아닙니다.");
        }
    }

    public SessionEnrollment getSessionEnrollment() {
        return sessionEnrollment;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionType getSessionType() {
        return sessionType;
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

    @Override
    public String toString() {
        return "SessionOption{" +
                "sessionStatus=" + sessionStatus +
                ", sessionType=" + sessionType +
                ", sessionEnrollment=" + sessionEnrollment +
                '}';
    }
}
