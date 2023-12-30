package nextstep.sessions.domain;

public enum SessionRecruitmentStatus {
    RECRUITING("모집중"),
    NON_RECRUITMENT("비모집중");

    private final String status;

    SessionRecruitmentStatus(String status) {
        this.status = status;
    }

    public boolean isRecruiting() {
        return this.status.equals(RECRUITING.status);
    }
}
