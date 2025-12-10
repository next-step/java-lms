package nextstep.courses.domain.session.policy.tuition;

public class PaidTuition implements TuitionPolicy {
    private final long tuitionFee;

    public PaidTuition(long tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    @Override
    public void validate(long payAmount) {
        if (payAmount != tuitionFee) {
            throw new IllegalArgumentException("수강료와 지불한 금액이 정확히 일치해야 합니다.");
        }
    }

    public long getTuitionFee() {
        return tuitionFee;
    }
}