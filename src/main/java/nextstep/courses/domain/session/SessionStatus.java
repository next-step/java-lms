package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;

public class SessionStatus {
    private SessionProgressStatus sessionProgressStatus;
    private SessionRecruitmentStatus sessionRecruitmentStatus;

    public SessionStatus() {
        this(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.CLOSED);
    }

    public SessionStatus(String sessionProgressStatus, String sessionRecruitmentStatus) {
        this(SessionProgressStatus.valueOf(sessionProgressStatus), SessionRecruitmentStatus.valueOf(sessionRecruitmentStatus));
    }

    public SessionStatus(SessionProgressStatus sessionProgressStatus, SessionRecruitmentStatus sessionRecruitmentStatus) {
        validate(sessionProgressStatus, sessionRecruitmentStatus);
        this.sessionProgressStatus = sessionProgressStatus;
        this.sessionRecruitmentStatus = sessionRecruitmentStatus;
    }

    private void validate(SessionProgressStatus sessionProgressStatus, SessionRecruitmentStatus sessionRecruitmentStatus) {
        if (!sessionProgressStatus.canRecuriting() && sessionRecruitmentStatus.isRecruiting()) {
            throw new IllegalArgumentException("종료된 강의는 모집할 수 없습니다.");
        }
    }

    public void canEnroll() throws CannotEnrollException {
        if (!sessionRecruitmentStatus.isRecruiting()) {
            throw new CannotEnrollException("강의가 모집중인 상태가 아닙니다.");
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
}
