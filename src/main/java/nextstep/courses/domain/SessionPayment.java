package nextstep.courses.domain;

public enum SessionPayment {
    FREE("무료"),
    PAID("유료");

    private final String status;

    SessionPayment(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
