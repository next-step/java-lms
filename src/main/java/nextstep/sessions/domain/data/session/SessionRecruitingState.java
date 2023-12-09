package nextstep.sessions.domain.data.session;

public enum SessionRecruitingState {

    RECRUITING("RECRUITING", "모집중"),
    NO_RECRUITING("NO_RECRUITING", "비모집중");
    private final String state;
    private final String title;

    SessionRecruitingState(String state, String title) {
        this.state = state;
        this.title = title;
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}
