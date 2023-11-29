package nextstep.courses.domain.type;

public enum SessionRecruitingStatus {

    RECRUITING("모집중"),
    NOT_RECRUITING("비모집중");

    private String description;

    SessionRecruitingStatus(String description) {
        this.description = description;
    }

    public static SessionRecruitingStatus defaultStatusOf(SessionProgressStatus progressStatus) {
        if (progressStatus.isTerminal()) {
            return NOT_RECRUITING;
        }
        return RECRUITING;
    }

    public boolean isRecruiting() {
        return this.equals(RECRUITING);
    }
}
