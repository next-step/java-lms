package nextstep.sessions.domain;

public class SessionStatus {

    private SessionProgressStatus progressStatus;

    private SessionRecruitmentStatus recruitmentStatus;

    public SessionStatus(SessionProgressStatus progressStatus, SessionRecruitmentStatus recruitmentStatus) {
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
    }

    public SessionProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public SessionRecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public boolean isNotRecruiting() {
        return !recruitmentStatus.isRecruiting();
    }
}
