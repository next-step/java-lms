package nextstep.courses.domain.session;

public enum SessionType {
    PAID("유료"),
    FREE("무료");

    SessionType(String value) {
        this.value = value;
    }

    private String value;

}
