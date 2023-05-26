package nextstep.courses.domain;

public enum PaymentType implements EnumModel {
    FREE("무료"),
    PAID("유료");

    private String payment;

    PaymentType(String payment) {
        this.payment = payment;
    }


    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return payment;
    }
}
