package nextstep.sessions.domain;

public enum RecruitmentState {
    NOT_RECRUITING("비모집중"),
    RECRUITING("모집중");

    private String text;

    RecruitmentState(String text) {
        this.text = text;
    }

    public boolean isNotRecruiting() {
        return this == NOT_RECRUITING;
    }
}
