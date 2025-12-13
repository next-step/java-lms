package nextstep.courses.domain;

public enum SessionType {
    FREE("무료"),
    PAID("유료"),
    ;

    private final String status;

    SessionType(String status) {
        this.status = status;
    }
}
