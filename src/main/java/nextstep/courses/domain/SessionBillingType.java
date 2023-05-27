package nextstep.courses.domain;

public enum SessionBillingType {
    FREE("무료"),
    PAID("유료");

    private final String type;

    SessionBillingType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
