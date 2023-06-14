package nextstep.courses.domain;

public class SessionStatus {

    private final SessionRecruitmentStatus sessionRecruitmentStatus;
    private final SessionProgressStatus sessionProgressStatus;

    public SessionStatus(SessionRecruitmentStatus sessionRecruitmentStatus, SessionProgressStatus sessionProgressStatus) {
        this.sessionRecruitmentStatus = sessionRecruitmentStatus;
        this.sessionProgressStatus = sessionProgressStatus;
    }

    public boolean canJoin() {
        return sessionProgressStatus.canJoin() && sessionRecruitmentStatus.canJoin();
    }

    public SessionProgressStatus getSessionProgressStatus() {
        return sessionProgressStatus;
    }

    public SessionRecruitmentStatus getSessionRecruitmentStatus() {
        return sessionRecruitmentStatus;
    }
}
