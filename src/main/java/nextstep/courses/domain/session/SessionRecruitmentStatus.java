package nextstep.courses.domain.session;

public enum SessionRecruitmentStatus {
    RECRUITING("모집중"),
    CLOSED("비모집중");
    private String displayName;

    SessionRecruitmentStatus(String displayName) {
        this.displayName = displayName;
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}
