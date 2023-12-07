package nextstep.sessions.domain;

public class SessionCharge {

    // 가격
    private long price;

    public SessionCharge(boolean charge, long price) {
        if (!charge) {
            this.price = 0;
            return;
        }
        if (price <= 0) {
            throw new IllegalArgumentException("유료 강의는 수강료가 0원 이하일 수 없습니다.");
        }
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
