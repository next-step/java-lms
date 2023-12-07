package nextstep.sessions.strategy;

public class PayEnrollment implements EnrollmentStrategy {
    public static final int INIT_COUNT = 0;
    private final int fee;
    private final int limit;
    private int studentCount;

    public PayEnrollment(int fee, int limit) {
        this.fee = fee;
        this.limit = limit;
        this.studentCount = INIT_COUNT;
    }

    @Override
    public void enrollment(int payMoney) {
        validateMoney(payMoney);
        validateLimit();
        studentCount++;
    }

    private void validateLimit() {
        if (limit == studentCount) {
            throw new IllegalArgumentException("최대 수강 인원을 초과했습니다.");
        }
    }

    private void validateMoney(int payMoney) {
        if (payMoney != fee) {
            throw new IllegalArgumentException("강의 가격과 결제 가격이 다릅니다.");
        }
    }
}
