package nextstep.courses.domain.session;

public enum State {
    READY("READY", "준비중"),
    RECRUIT_START("RECRUIT_START", "모집중"),
    RECRUIT_END("RECRUIT_END", "모집종료"),
    SESSION_START("SESSION_START", "강의중"),
    SESSION_END("SESSION_END", "강의종료");

    private String code;
    private String description;

    State(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
