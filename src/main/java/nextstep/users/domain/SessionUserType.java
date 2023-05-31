package nextstep.users.domain;

public enum SessionUserType {
    USER("user"),
    INSTRUCTOR("instructor");

    private String code;

    SessionUserType(String code) {
        this.code = code;
    }
}
