package nextstep.sessions.domain;

public class PaidSessionType implements SessionType {

    private Long amount;
    private int maxCapacity;

    public PaidSessionType(Long amount, int maxCapacity) {
        this.amount = amount;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public boolean isPossibleToRegister(Long paidAmount, int enrolledStudents) {
        if (!amount.equals(paidAmount)) {
            throw new IllegalArgumentException("수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능합니다.");
        }

        if (maxCapacity < enrolledStudents + 1) {
            throw new IllegalArgumentException("강의 최대 수강 인원을 초과할 수 없습니다.");
        }
        return true;
    }
}
