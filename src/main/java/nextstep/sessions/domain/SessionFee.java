package nextstep.sessions.domain;

public enum SessionFee {

    FREE("FREE", "무료"),
    PAY("PAY", "유료");

    private final String fee;
    private final String title;

    SessionFee(String fee, String title) {
        this.fee = fee;
        this.title = title;
    }
}
