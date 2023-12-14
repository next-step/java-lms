package nextstep.sessions.domain;

public class FreeSessionType implements SessionType {

    private Long amount;
    private int maxCapacity;

    public FreeSessionType() {
        this.amount = 0L;
        this.maxCapacity = Integer.MAX_VALUE;
    }

    @Override
    public boolean isPossibleToRegister(Long paidAmount, int enrolledStudents) {
        return true;
    }
}
