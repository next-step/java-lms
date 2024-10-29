package nextstep.session.domain;

public enum PaymentType {
    FREE("무료"),
    PAID("유료");

    private final String name;

    PaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
