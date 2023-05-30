package nextstep.courses.domain;

public enum SessionStatus {

    PREPARING(SessionRecruitStatus.OPEN),
    RECRUITING(SessionRecruitStatus.OPEN),
    IN_PROGRESS(SessionRecruitStatus.OPEN),
    CLOSED(SessionRecruitStatus.CLOSED);

    private final SessionRecruitStatus sessionProgressStatus;

    SessionStatus(SessionRecruitStatus sessionProgressStatus) {
        this.sessionProgressStatus = sessionProgressStatus;
    }
}
