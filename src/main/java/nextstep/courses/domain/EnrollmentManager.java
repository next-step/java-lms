package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class EnrollmentManager {

    private final SessionFee fee;
    private final EnrollmentCount count;
    private final SessionStatus status;

    public EnrollmentManager(Long fee, Integer count) {
        this(new SessionFee(fee), new EnrollmentCount(count), SessionStatus.RECRUITING);
    }

    public EnrollmentManager(Long fee, Integer count, String status) {
        this(new SessionFee(fee), new EnrollmentCount(count), SessionStatus.valueOf(status));
    }

    public EnrollmentManager(Long fee, Integer count, SessionStatus status) {
        this(new SessionFee(fee), new EnrollmentCount(count), status);
    }

    private EnrollmentManager(SessionFee fee, EnrollmentCount count, SessionStatus status) {
        this.fee = fee;
        this.count = count;
        this.status = status;
    }

    public boolean canEnroll(Payment payment) {
        return status.isRecruiting() && (fee.isFree() || count.hasRemainingCount() && fee.canPurchase(payment));
    }

    public EnrollmentManager decreaseCount() {
        return new EnrollmentManager(this.fee, this.count.decreaseCount(), this.status);
    }

    public Long getFee() {
        return fee.getFee();
    }

    public Integer getCount() {
        return count.getCount();
    }

    public String getStatus() {
        return status.name();
    }
}
