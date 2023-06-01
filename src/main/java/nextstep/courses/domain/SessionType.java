package nextstep.courses.domain;

public enum SessionType {

    FREE("무료"),
    CHANGED("유료")
    ;

    private String name;
    SessionType(String name) {
        this.name = name;
    }
}
