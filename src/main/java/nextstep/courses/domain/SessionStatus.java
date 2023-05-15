package nextstep.courses.domain;

public enum SessionStatus {
    READY("준비"),
    OPEN("모집"),
    CLOSED("종료")
    ;

    private final String name;

    SessionStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isOpen() {
        return this == OPEN;
    }
}
