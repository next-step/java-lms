package nextstep.sessions.domain.data.type;

public enum PaidType {

    FREE("FREE", "무료"),
    PAID("PAID", "유료");

    private final String type;
    private final String title;

    PaidType(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public boolean isPaid() {
        return this == PAID;
    }

    public boolean isFree() {
        return this == FREE;
    }
}
