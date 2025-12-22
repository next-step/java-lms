package nextstep.sessions.domain.enrollment;

import nextstep.sessions.domain.SessionPricing;
import nextstep.sessions.domain.SessionStatus;

public class SessionEnrollment {

    public static final String ERROR_SESSION_NOT_OPEN = "모집중인 강의만 수강 신청 가능합니다.";
    public static final String ERROR_CAPACITY_EXCEEDED = "강의 정원이 초과되어 수강 신청할 수 없습니다.";
    public static final String ERROR_PAYMENT_AMOUNT_MISMATCH = "결제 금액이 강의 수강료와 일치하지 않습니다.";

    private SessionStatus status;
    private SessionPricing pricing;
    private Capacity capacity;
    private Enrollments enrollments;

    SessionEnrollment(SessionPricing pricing, Capacity capacity) {
        this.status = SessionStatus.PREPARING;
        this.pricing = pricing;
        this.capacity = capacity;
        this.enrollments = new Enrollments();
    }

    public static SessionEnrollment paidLimited(int fee, int maxCapacity) {
        return new SessionEnrollment(
                SessionPricing.paid(fee),
                Capacity.limited(maxCapacity)
        );
    }

    public static SessionEnrollment freeUnlimited() {
        return new SessionEnrollment(
                SessionPricing.free(),
                Capacity.unlimited()
        );
    }

    public SessionStatus status() {
        return status;
    }

    public void startRecruiting() {
        this.status = SessionStatus.OPEN;
    }

    public void enroll(Enrollment enrollment) {
        validateOpen();
        validateCapacity();
        validatePayment(enrollment);
        enrollments.add(enrollment);
    }

    private void validateOpen() {
        if (status != SessionStatus.OPEN) {
            throw new IllegalStateException(ERROR_SESSION_NOT_OPEN);
        }
    }

    private void validateCapacity() {
        if (!capacity.canEnroll(enrollments.size())) {
            throw new IllegalStateException(ERROR_CAPACITY_EXCEEDED);
        }
    }

    private void validatePayment(Enrollment enrollment) {
        if (!enrollment.canPayFor(pricing)) {
            throw new IllegalArgumentException(ERROR_PAYMENT_AMOUNT_MISMATCH);
        }
    }
}
