package nextstep.courses.domain;

public enum PriceType {
    PAID("유료"),
    FREE("무료");

    private final String type;

    PriceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
