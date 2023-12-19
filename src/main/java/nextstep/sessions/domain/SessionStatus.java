package nextstep.sessions.domain;

import nextstep.common.Period;

public class SessionStatus {

    private SessionProgressStatus progressStatus;

    private SessionRecruitmentStatus recruitmentStatus;

    public SessionStatus(Period date) {
        if (date.isPreparing()) {
            progressStatus = SessionProgressStatus.PREPARING;
            recruitmentStatus = SessionRecruitmentStatus.RECRUITING;
            return;
        }
        if (date.isInProgress()) {
            progressStatus = SessionProgressStatus.PROGRESSING;
            recruitmentStatus = SessionRecruitmentStatus.RECRUITING;
            return;
        }
        progressStatus = SessionProgressStatus.END;
        recruitmentStatus = SessionRecruitmentStatus.NON_RECRUITMENT;
    }

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
        if (recruitmentStatus == SessionRecruitmentStatus.NON_RECRUITMENT) {
            return true;
        }
        return false;
    }
}
