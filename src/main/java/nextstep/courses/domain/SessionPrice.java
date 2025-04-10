package nextstep.courses.domain;

public class SessionPrice {
    private final SessionType type;
    private final int price;

    public SessionPrice(SessionType type, int price) {
        validatePrice(type, price);
        this.type = type;
        this.price = price;
    }

    private void validatePrice(SessionType type, int price) {
        if (type.isPaid() && price <= 0) {
            throw new IllegalArgumentException("유료 강의는 수강료가 0보다 커야 합니다.");
        }
    }

    public boolean isPaid() {
        return type.isPaid();
    }

    public int getPrice() {
        return price;
    }

    public void validatePayment(Long paymentAmount) {
        if (isPaid() && paymentAmount != price) {
            throw new IllegalArgumentException("결제 금액이 수강료와 일치하지 않습니다.");
        }
    }
} 