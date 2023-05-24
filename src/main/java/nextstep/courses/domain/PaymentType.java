package nextstep.courses.domain;

public enum PaymentType {
    FREE("무료"),
    PAID("유료");

    private String payment;

    PaymentType(String payment) {
        this.payment = payment;
    }
}
