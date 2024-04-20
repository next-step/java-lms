package nextstep.courses.domain.Session;

public class Student {
    private int fee = 0 ;

    public void pay(int fee) {
        this.fee = fee;
    }

    public int getFee() {
        return this.fee;
    }

    public void isPaid(int sessionFee) {
        if (fee != sessionFee) {
            throw new RuntimeException("수강생이 결제한 금액과 수강료가 일치하지 않습니다. 결제한 금액 : " + fee + " 수강료 : " + sessionFee);
        }
    }
}
