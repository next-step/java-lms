package nextstep.courses.domain;

public enum SessionState {

    PREPARING("준비중", false, true),
    RECRUITING("모집중", true, false),
    PROGRESSING("진행중", false, false),
    FINISH("종료", false, false);

    private final String desc;
    private final boolean availableRecruitment;
    private final boolean availableManualChangeSession;

    SessionState(String desc, boolean availableRecruitment, boolean availableManualChangeSession) {
        this.desc = desc;
        this.availableRecruitment = availableRecruitment;
        this.availableManualChangeSession = availableManualChangeSession;
    }

    public boolean isAvailableRecruitment() {
        return this.availableRecruitment;
    }

    public boolean isAvailableManualChangeSession() {
        return availableManualChangeSession;
    }
}
