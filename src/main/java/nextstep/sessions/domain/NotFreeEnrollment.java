package nextstep.sessions.domain;

public class NotFreeEnrollment implements EnrollmentCondition {
    public static final int INIT_COUNT = 0;
    private final int cost;
    private final int limit;
    private int studentCount;

    public NotFreeEnrollment(int payMoney, int limit) {
        this.cost = payMoney;
        this.limit = limit;
        this.studentCount = INIT_COUNT;
    }

    @Override
    public void enrollment(int payMoney) {
        validateLimit();
        validatePay(payMoney);
        studentCount++;
    }

    private void validateLimit() {
        if (limit == studentCount) {
            throw new IllegalArgumentException("최대 수강 인원을 초과했습니다.");
        }
    }

    private void validatePay(int payMoney) {
        if (cost != payMoney) {
            throw new IllegalArgumentException("강의 가격과 지불하신 돈이 일치하지 않습니다.");
        }
    }
}
