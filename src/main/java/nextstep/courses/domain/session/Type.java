package nextstep.courses.domain.session;

public enum Type {
    PAID("paid"),
    FREE("free");

    private final String type;

    Type(String type) {
        this.type = type;
    }
}
