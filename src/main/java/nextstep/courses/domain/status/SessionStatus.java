package nextstep.courses.domain.status;

public class SessionStatus {

    private final ProgressStatus progressStatus;
    private final RecruitmentStatus recruitmentStatus;

    public SessionStatus(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus) {
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
    }

    public static SessionStatus of(String progressString, String recruitmentString) {
        ProgressStatus progressStatus = ProgressStatus.convert(progressString);
        RecruitmentStatus recruitmentStatus = RecruitmentStatus.convert(recruitmentString);
        return new SessionStatus(progressStatus, recruitmentStatus);
    }

    public boolean cannotEnroll() {
        return recruitmentStatus.cannotEnroll();
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }
}
