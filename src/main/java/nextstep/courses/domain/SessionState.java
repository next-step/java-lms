package nextstep.courses.domain;

public enum SessionState {

    PREPARING("준비중", false),
    RECRUITING("모집중", true),
    PROGRESSING("진행중", false),
    FINISH("종료", false);

    private final String desc;
    private final boolean availableRecruitment;

    SessionState(String desc, boolean availableRecruitment) {
        this.desc = desc;
        this.availableRecruitment = availableRecruitment;
    }

    public boolean isAvailableRecruitment() {
        return this.availableRecruitment;
    }
}
