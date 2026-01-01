package nextstep.courses.domain;

public enum SessionRecruitingStatus {
    RECRUITING("모집중"),
    CLOSE("비모집중"),
    ;

    private final String status;

    SessionRecruitingStatus(String status) {
        this.status = status;
    }

    public boolean enableRecruiting() {
        return this == SessionRecruitingStatus.RECRUITING;
    }
}
