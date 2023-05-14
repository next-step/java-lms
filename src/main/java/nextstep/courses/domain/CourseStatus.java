package nextstep.courses.domain;

public enum CourseStatus {
    READY("준비"),
    OPEN("모집"),
    CLOSED("종료")
    ;

    private final String name;

    CourseStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isOpen() {
        return this == OPEN;
    }
}
