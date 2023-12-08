package nextstep.courses.domain;

public enum PayType {

    FREE("무료"),
    PAID("유료");

    private final String name;

    PayType(String name) {
        this.name = name;
    }
}
