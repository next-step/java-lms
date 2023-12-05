package nextstep.courses.domain;

public enum SessionType {
    PAID("유료"),
    FREE("무료");

    private String type;

    SessionType (String type) {
        this.type = type;
    }
}
