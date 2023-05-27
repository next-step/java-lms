package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session extends BaseTime {

    private final SessionPeriod sessionPeriod;
    private final String name;
    private final SessionStatus sessionStatus;
    private final Image coverImage;
    private final Students students;
    private final PaymentStrategy paymentStrategy;

    public Session(SessionPeriod sessionPeriod, String name, SessionStatus sessionStatus, Image coverImage, Students students, PaymentStrategy paymentStrategy) {
        this(sessionPeriod, name, sessionStatus, coverImage, students, paymentStrategy, LocalDateTime.now(), null);
    }

    public Session(SessionPeriod sessionPeriod, String name, SessionStatus sessionStatus, Image coverImage, Students students, PaymentStrategy paymentStrategy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.sessionPeriod = sessionPeriod;
        this.name = name;
        this.sessionStatus = sessionStatus;
        this.coverImage = coverImage;
        this.students = students;
        this.paymentStrategy = paymentStrategy;
        validateSessionStatusAndEnrolledStudents();
    }

    private void validateSessionStatusAndEnrolledStudents() {
        if (sessionStatus.isPreparing() && hasEnrolledStudent()) {
            throw new IllegalArgumentException("there should be no students when the session is in preparation");
        }
    }

    public int currentEnrolmentCount() {
        return students.countEnrollmentCount();
    }

    public boolean hasEnrolledStudent() {
        return !students.isEmpty();
    }

    public void enrollInSession(NsUser nsUser) {
        if (sessionStatus.isEnrollmentPossible()) {
            students.addStudent(nsUser);
        }
    }

    public int sessionDuration() {
        return sessionPeriod.sessionDuration();
    }

    public int sessionPrice() {
        return paymentStrategy.priceValue();
    }

    public String fetchName() {
        return name;
    }
}
