package nextstep.courses.domain.session;

public class Student {
    public Student() {}

    public Student(int fee) {
        this.fee = fee;
    }

    private int fee = 0 ;

    public int getFee() {
        return this.fee;
    }

    public void isPaid(int sessionFee) {
        if (fee != sessionFee) {
            throw new RuntimeException("수강생이 결제한 금액과 수강료가 일치하지 않습니다. 결제한 금액 : " + fee + " 수강료 : " + sessionFee);
        }
    }
}
