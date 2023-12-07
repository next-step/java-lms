package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.util.Assert;

public class Enrollment {
    private Long price;
    private ChargeStatus chargeStatus;
    private RecruitingStatus recruitingStatus;
    private SessionStatus status;
    private SessionStudent sessionStudent;

    public Enrollment(final Long price, final TotalSelectStatusUsers totalSelectStatusUsers) {
        validateEnrollment(price);

        this.price = price;

        this.chargeStatus = ChargeStatus.decide(price);
        this.status = SessionStatus.READY;
        this.recruitingStatus = RecruitingStatus.NOT_RECRUITING;
        this.sessionStudent = new SessionStudent(15, totalSelectStatusUsers);
    }

    private void validateEnrollment(final Long price) {
        Assert.isTrue(price != null && price >= 0, "price cannot be negative");
    }

    public void enroll(final Payment payment, final NsUser user) {
        if (isNotRecruiting()) {
            throw new IllegalStateException("session is not recruiting");
        }

        if (isPaidSession()) {
            validateIfPaidSession(payment);
        }

        increaseEnrollment(user);
    }

    private boolean isPaidSession() {
        return this.chargeStatus.isPaid();
    }

    private void validateIfPaidSession(final Payment payment) {
        if (payment.isNotPaid(getPrice())) {
            throw new IllegalStateException("paid amount is different with price");
        }

        if (isReachedMaxStudentLimit()) {
            throw new IllegalStateException("max student limit is reached");
        }
    }

    private boolean isReachedMaxStudentLimit() {
        return this.sessionStudent.isReachedMaxStudentLimit();
    }

    public long getPrice() {
        return this.price;
    }

    private void increaseEnrollment(final NsUser user) {
        this.sessionStudent.increaseStudentCount(user);
    }

    public void setStatus(final SessionStatus status) {
        this.status = status;
    }

    public int getCurrentStudentCount() {
        return this.sessionStudent.getCurrentStudentCount();
    }

    public boolean isRecruiting() {
        return !isNotRecruiting();
    }

    private boolean isNotRecruiting() {
        return !this.recruitingStatus.isRecruiting();
    }

    public int getMaxStudentLimit() {
        return this.sessionStudent.getMaxStudentLimit();
    }

    public String getSessionStatusString() {
        return this.status.toString();
    }

    public String getRecruitingStatusString() {
        return this.recruitingStatus.toString();
    }

    public void changeMaxStudentLimit(final int maxStudentLimit) {
        this.sessionStudent.changeMaxStudentLimit(maxStudentLimit);
    }

    public void setRecruitingStatus(final RecruitingStatus recruitingStatus) {
        this.recruitingStatus = recruitingStatus;
    }

    public TotalSelectStatusUsers getSelectionUsers() {
        return this.sessionStudent.getSelectionUsers();
    }
}
