package nextstep.courses.domain.session;

public class Student {
    public Student(Long sessionId, Long loginUserId) {
        this.sessionId = sessionId;
        this.loginUserId = loginUserId;
    }

    private final Long sessionId;
    private final Long loginUserId;
    private int fee = 0;

    public int getFee() {
        return this.fee;
    }

    public Long getSessionId() {
        return this.sessionId;
    }

    public Long getLoginUserId() {
        return this.loginUserId;
    }

    public void pay(int fee) {
        this.fee = fee;
    }

    public void isPaid(int sessionFee) {
        if (fee != sessionFee) {
            throw new RuntimeException("수강생이 결제한 금액과 수강료가 일치하지 않습니다. 결제한 금액 : " + fee + " 수강료 : " + sessionFee);
        }
    }
}
