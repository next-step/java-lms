package nextstep.courses.domain;

public enum SessionType {
    FREE("무료"),
    PAID("유료")
    ;

    private final String name;

    SessionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
