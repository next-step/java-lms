package nextstep.courses.domain.session;

public enum ChargeStatus {
    FREE("무료"),
    PAID("유료");

    private final String description;

    ChargeStatus(final String description) {
        this.description = description;
    }

    public boolean isPaid() {
        return this == PAID;
    }

    public static ChargeStatus decide(final long price) {
        if (price == 0) {
            return FREE;
        }

        return PAID;
    }
}
