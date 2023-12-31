package nextstep.sessions.domain;

public class SessionCharge {
    /*
    강의 가격
    강의 가격, 수강 인원을 관리한다.
    유료 강의의 경우 수강 인원에 제한이 있다.
     */

    private long price;

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

    public void checkRecruits(int studentCount) {
        if (this.limitCount > 0 && this.limitCount == studentCount) {
            throw new IllegalStateException("모집 인원이 마감되었습니다.");
        }
    }
}
