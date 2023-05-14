package nextstep.courses.domain;

public enum CourseType {
    FREE("무료"),
    PAID("유료")
    ;

    private final String name;

    CourseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
