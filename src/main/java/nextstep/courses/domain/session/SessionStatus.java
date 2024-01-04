package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;

import java.util.Objects;

public class SessionStatus {
    private final SessionProgressStatus sessionProgressStatus;
    private final SessionRecruitmentStatus sessionRecruitmentStatus;

    public SessionStatus(String sessionProgressStatus, String sessionRecruitmentStatus) {
        this(SessionProgressStatus.get(sessionProgressStatus), SessionRecruitmentStatus.valueOf(sessionRecruitmentStatus));
    }

    public SessionStatus(SessionProgressStatus sessionProgressStatus, SessionRecruitmentStatus sessionRecruitmentStatus) {
        validate(sessionProgressStatus, sessionRecruitmentStatus);
        this.sessionProgressStatus = sessionProgressStatus;
        this.sessionRecruitmentStatus = sessionRecruitmentStatus;
    }

    private void validate(SessionProgressStatus sessionProgressStatus, SessionRecruitmentStatus sessionRecruitmentStatus) {
        if (sessionProgressStatus.isFinished() && sessionRecruitmentStatus.isRecruiting()) {
            throw new IllegalArgumentException("종료된 강의는 모집할 수 없습니다.");
        }
    }

    public void canEnroll() throws CannotEnrollException {
        if (!sessionRecruitmentStatus.isRecruiting()) {
            throw new CannotEnrollException("강의가 모집중인 상태가 아닙니다.");
        }
        if (sessionProgressStatus.isFinished()) {
            throw new CannotEnrollException("강의가 종료되었습니다.");
        }
    }

    public SessionRecruitmentStatus sessionRecruitmentStatus() {
        return sessionRecruitmentStatus;
    }

    public SessionProgressStatus sessionProgressStatus() {
        return sessionProgressStatus;
    }

    @Override
    public String toString() {
        return "SessionStatus{" +
                "sessionProgressStatus=" + sessionProgressStatus +
                ", sessionRecruitmentStatus=" + sessionRecruitmentStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionStatus that = (SessionStatus) o;
        return sessionProgressStatus == that.sessionProgressStatus && sessionRecruitmentStatus == that.sessionRecruitmentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionProgressStatus, sessionRecruitmentStatus);
    }
}
