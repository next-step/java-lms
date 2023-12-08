package nextstep.courses.domain.session;

public enum SessionType {

    FREE("FREE"),
    PAID("PAID"),
    ;

    private final String name;

    SessionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isFree() {
        return this.equals(FREE);
    }
}
