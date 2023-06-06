package nextstep.courses.domain;

public class SessionStatus {
    private final SessionProgressStatus sessionProgressStatus;
    private final SessionRecruitmentStatus sessionRecruitmentStatus;

    public SessionStatus(SessionProgressStatus sessionProgressStatus, SessionRecruitmentStatus sessionRecruitmentStatus) {
        this.sessionProgressStatus = sessionProgressStatus;
        this.sessionRecruitmentStatus = sessionRecruitmentStatus;
    }

    public boolean canEnroll() {
        return sessionProgressStatus.canEnroll() && sessionRecruitmentStatus.canEnroll();
    }

    public String getProgressStatus() {
        return sessionProgressStatus.getKey();
    }

    public String getRecruitmentStatus() {
        return sessionRecruitmentStatus.getKey();
    }
}
