package nextstep.courses.domain.session;

public enum SessionStatus {

    PREPARING("PREPARING"),
    RECRUITING("RECRUITING"),
    FINISHED("FINISHED"),
    ;

    private final String name;

    SessionStatus(String name) {
        this.name = name;
    }

    public boolean isNotRecruiting() {
        return !this.equals(RECRUITING);
    }

    public String getName() {
        return name;
    }
}
