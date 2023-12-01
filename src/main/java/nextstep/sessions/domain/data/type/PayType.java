package nextstep.sessions.domain.data.type;

public enum PayType {

    FREE("FREE", "무료"),
    PAY("PAY", "유료");

    private final String type;
    private final String title;

    PayType(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public boolean isPay() {
        return this == PAY;
    }
}
