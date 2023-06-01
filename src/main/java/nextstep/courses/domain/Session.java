package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session extends BaseTime {

    private final SessionInformation sessionInformation;
    private final SessionPeriod sessionPeriod;
    private final SessionStatus sessionStatus;
    private final Image coverImage;
    private final PaymentStrategy paymentStrategy;
    private final Enrollment enrollment;

    public Session(SessionInformation sessionInformation, SessionPeriod sessionPeriod, SessionStatus sessionStatus, Image coverImage, PaymentStrategy paymentStrategy, Enrollment enrollment) {
        this(sessionInformation, sessionPeriod, sessionStatus, coverImage, paymentStrategy, enrollment, LocalDateTime.now(), null);
    }

    public Session(SessionInformation sessionInformation, SessionPeriod sessionPeriod, SessionStatus sessionStatus, Image coverImage, PaymentStrategy paymentStrategy, Enrollment enrollment, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.sessionInformation = sessionInformation;
        this.sessionPeriod = sessionPeriod;
        this.sessionStatus = sessionStatus;
        this.coverImage = coverImage;
        this.paymentStrategy = paymentStrategy;
        this.enrollment = enrollment;
        validateSessionStatusAndEnrolledStudents();
    }

    public int currentEnrolmentCount() {
        return enrollment.currentEnrolmentCount();
    }

    public boolean hasEnrolledStudent() {
        return enrollment.hasEnrolledStudent();
    }

    public void enrollInSession(NsUser nsUser) {
        if (!sessionStatus.isEnrolling()) {
            throw new CannotEnrollException();
        }
        enrollment.enroll(nsUser);
    }

    private void validateSessionStatusAndEnrolledStudents() {
        if (sessionStatus.isPreparing() && hasEnrolledStudent()) {
            throw new IllegalArgumentException("there should be no students when the session is in preparation");
        }
    }

    public int sessionDuration() {
        return sessionPeriod.sessionDuration();
    }

    public int sessionCharge() {
        return paymentStrategy.chargeValue();
    }

    public String sessionTitle() {
        return sessionInformation.fetchTitle();
    }
}
