package nextstep.session.domain;

public enum SessionType {
    FREE("무료"),
    PAID("유료");

    public final String type;

    SessionType(String type) {
        this.type = type;
    }
}
