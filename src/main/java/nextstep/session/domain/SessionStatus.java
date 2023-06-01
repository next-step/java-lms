package nextstep.session.domain;

import nextstep.session.NotProceedingException;
import nextstep.session.NotRecruitingException;

public class SessionStatus {

    private ProgressStatus progressStatus;
    private RecruitmentStatus recruitmentStatus;

    public SessionStatus(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus) {
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
    }

    public void checkSessionStatus() throws NotProceedingException {
        if (!progressStatus.equals(ProgressStatus.PROCEEDING)) {
            throw new NotProceedingException("강의 진행 중에만 신청 가능합니다.");
        }

        if (!recruitmentStatus.equals(RecruitmentStatus.RECRUITING)) {
            throw new NotRecruitingException("모집 중에만 신청 가능합니다.");
        }
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }
}
