package nextstep.sessions.domain.data.type;

public enum PayType {

    FREE("FREE", "무료"),
    PAID("PAID", "유료");

    private final String type;
    private final String title;

    PayType(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public boolean isPaid() {
        return this == PAID;
    }
}
