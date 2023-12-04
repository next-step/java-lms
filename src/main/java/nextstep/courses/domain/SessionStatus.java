package nextstep.courses.domain;

public class SessionStatus {

    private final SessionStatusType sessionStatusType;

    private final RecruitmentStatusType recruitmentStatusType;

    public SessionStatus(SessionStatusType sessionStatusType, RecruitmentStatusType recruitmentStatusType) {
        this.sessionStatusType = sessionStatusType;
        this.recruitmentStatusType = recruitmentStatusType;
    }

    public SessionStatus(SessionStatusType sessionStatusType) {
        this.sessionStatusType = sessionStatusType;

        if (sessionStatusType.isEndSession()) {
            this.recruitmentStatusType = RecruitmentStatusType.NOT_RECRUITING;
            return;
        }

        this.recruitmentStatusType = RecruitmentStatusType.RECRUITING;
    }

    public boolean isRecruitment() {
        return this.recruitmentStatusType == RecruitmentStatusType.RECRUITING;
    }

    public String sessionStatusType() {
        return this.sessionStatusType.name();
    }

    public String recruitmentStatusType() {
        return this.recruitmentStatusType.name();
    }
}
