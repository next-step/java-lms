package nextstep.courses.domain;

public enum State {
    READY("준비중"), RECRUIT_START("모집중"), RECRUIT_END("모집종료"), SESSION_START("강의중"), SESSION_END("강의종료");

    private String description;

    State(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
