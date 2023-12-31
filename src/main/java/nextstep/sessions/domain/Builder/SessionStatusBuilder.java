package nextstep.sessions.domain.Builder;

import nextstep.sessions.domain.SessionProgressStatus;
import nextstep.sessions.domain.SessionRecruitmentStatus;
import nextstep.sessions.domain.SessionStatus;

public class SessionStatusBuilder {
    private SessionProgressStatus progressStatus = SessionProgressStatus.PREPARING;
    private SessionRecruitmentStatus recruitmentStatus = SessionRecruitmentStatus.RECRUITING;

    public SessionStatusBuilder withProgressStatus(SessionProgressStatus progressStatus) {
        this.progressStatus = progressStatus;
        return this;
    }

    public SessionStatusBuilder withRecruitmentStatus(SessionRecruitmentStatus recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
        return this;
    }

    public SessionStatus build() {
        return new SessionStatus(progressStatus, recruitmentStatus);
    }
}
