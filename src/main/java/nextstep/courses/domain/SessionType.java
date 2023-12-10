package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class SessionType {

    private PayType type;
    private Long price;
    private Integer capacity;

    public SessionType() {
        this.type = PayType.FREE;
    }

    public SessionType(PayType type, Long price, Integer capacity) {
        this.type = type;
        this.price = price;
        this.capacity = capacity;
    }

    public boolean isMaxCapacity(Students students) {
        if (isFreeSession()) {
            return false;
        }
        return capacity < students.size();
    }

    public boolean isEqualPrice(Payment payment) {
        if (isFreeSession()) {
            return true;
        }
        return payment.isEqualPrice(price);
    }

    private boolean isFreeSession() {
        return PayType.FREE.equals(type);
    }

    public PayType type() {
        return type;
    }

    public Long price() {
        return price;
    }

    public Integer capacity() {
        return capacity;
    }
}
