package nextstep.courses.domain;

public enum SessionState {

    PREPARING("준비중", false, true, true, false),
    RECRUITING("모집중", true, false, false, true),
    END_OF_RECRUITMENT("모집종료", false, false, false, true),
    PROGRESSING("진행중", false, false, true, false),
    FINISH("종료", false, false, false, false);

    private final String desc;
    private final boolean availableRecruitment;
    private final boolean availableManualChangeSession;
    private final boolean allowSessionState;
    private final boolean allowRecruitmentState;

    SessionState(
            String desc,
            boolean availableRecruitment,
            boolean availableManualChangeSession,
            boolean allowSessionState,
            boolean allowRecruitmentState
    ) {
        this.desc = desc;
        this.availableRecruitment = availableRecruitment;
        this.availableManualChangeSession = availableManualChangeSession;
        this.allowSessionState = allowSessionState;
        this.allowRecruitmentState = allowRecruitmentState;
    }

    public boolean isAvailableRecruitment() {
        return this.availableRecruitment;
    }

    public boolean isAvailableManualChangeSession() {
        return availableManualChangeSession;
    }

    public boolean isAllowSessionState() {
        return allowSessionState;
    }

    public boolean isAllowRecruitmentState() {
        return allowRecruitmentState;
    }
}
