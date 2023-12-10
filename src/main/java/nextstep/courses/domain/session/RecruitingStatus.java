package nextstep.courses.domain.session;

public enum RecruitingStatus {
    RECRUITING("모집중"),
    NOT_RECRUITING("비모집중");
    private final String displayName;

    RecruitingStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}
