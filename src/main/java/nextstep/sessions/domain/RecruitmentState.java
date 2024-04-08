package nextstep.sessions.domain;

public enum RecruitmentState {
    NOT_RECRUITING("비모집중"),
    RECRUITING("모집중");

    private String name;

    RecruitmentState(String name) {
        this.name = name;
    }
}
