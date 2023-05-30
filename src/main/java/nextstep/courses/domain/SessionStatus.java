package nextstep.courses.domain;

public enum SessionStatus {

    PREPARING(SessionRecruitStatus.OPEN),
    RECRUITING(SessionRecruitStatus.OPEN),
    IN_PROGRESS(SessionRecruitStatus.OPEN),
    CLOSED(SessionRecruitStatus.CLOSED);

    private final SessionRecruitStatus sessionRecruitStatus;

    SessionStatus(SessionRecruitStatus sessionProgressStatus) {
        this.sessionRecruitStatus = sessionProgressStatus;
    }

    public boolean isRecruitStatus() {
        return this.sessionRecruitStatus == SessionRecruitStatus.OPEN;
    }
}
