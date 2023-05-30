package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session extends BaseTime {

    private final String sessionName;
    private final SessionPeriod sessionPeriod;
    private final SessionStatus sessionStatus;
    private final Image coverImage;
    private final PaymentStrategy paymentStrategy;
    private final Enrollment enrollment;

    public Session(String sessionName, SessionPeriod sessionPeriod, SessionStatus sessionStatus, Image coverImage, PaymentStrategy paymentStrategy, Enrollment enrollment) {
        this(sessionName, sessionPeriod, sessionStatus, coverImage, paymentStrategy, enrollment, LocalDateTime.now(), null);
    }

    public Session(String sessionName, SessionPeriod sessionPeriod, SessionStatus sessionStatus, Image coverImage, PaymentStrategy paymentStrategy, Enrollment enrollment, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.sessionName = sessionName;
        this.sessionPeriod = sessionPeriod;
        this.sessionStatus = sessionStatus;
        this.coverImage = coverImage;
        this.paymentStrategy = paymentStrategy;
        this.enrollment = enrollment;
        validateSessionStatusAndEnrolledStudents();
    }

    private void validateSessionStatusAndEnrolledStudents() {
        if (sessionStatus.isPreparing() && hasEnrolledStudent()) {
            throw new IllegalArgumentException("there should be no students when the session is in preparation");
        }
    }

    public int currentEnrolmentCount() {
        return enrollment.currentEnrolmentCount();
    }

    public boolean hasEnrolledStudent() {
        return enrollment.hasEnrolledStudent();
    }

    public void enrollInSession(NsUser nsUser) {
        if (sessionStatus.isEnrollmentPossible()) {
            enrollment.enroll(nsUser);
        }
    }

    public int sessionDuration() {
        return sessionPeriod.sessionDuration();
    }

    public int sessionPrice() {
        return paymentStrategy.priceValue();
    }

    public String fetchName() {
        return sessionName;
    }
}
