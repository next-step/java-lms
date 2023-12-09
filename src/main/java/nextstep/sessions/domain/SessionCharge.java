package nextstep.sessions.domain;

public class SessionCharge {

    // 가격
    private long price;

    // 제한 인원
    private int limitCount;

    public SessionCharge(boolean charge, long price, int limitCount) {
        if (!charge) {
            this.price = 0;
            this.limitCount = 0;
            return;
        }
        if (price <= 0) {
            throw new IllegalArgumentException("유료 강의는 수강료가 0원 이하일 수 없습니다.");
        }
        if (limitCount <= 0) {
            throw new IllegalArgumentException("유료 강의는 수강 인원에 제한 있습니다.");
        }
        this.price = price;
        this.limitCount = limitCount;
    }

    public double getPrice() {
        return price;
    }

    public int getLimitCount() {
        return limitCount;
    }
}
