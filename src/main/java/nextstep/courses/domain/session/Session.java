package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.BaseTime;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.Student;
import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.payment.PaymentStrategy;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session extends BaseTime {

    private final Long id;
    private final SessionInformation sessionInformation;
    private final SessionPeriod sessionPeriod;
    private final SessionStatus sessionStatus;
    private final Image coverImage;
    private final PaymentStrategy paymentStrategy;
    private final Enrollment enrollment;

    public Session(Long id, SessionInformation sessionInformation, SessionPeriod sessionPeriod, SessionStatus sessionStatus, Image coverImage, PaymentStrategy paymentStrategy, Enrollment enrollment) {
        this(id, sessionInformation, sessionPeriod, sessionStatus, coverImage, paymentStrategy, enrollment, LocalDateTime.now(), null);
    }

    public Session(Long id, SessionInformation sessionInformation, SessionPeriod sessionPeriod, SessionStatus sessionStatus, Image coverImage, PaymentStrategy paymentStrategy, Enrollment enrollment, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
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

    public Student enrollInSession(NsUser nsUser) {
        return enrollInSession(nsUser, new ArrayList<>());
    }

    public Student enrollInSession(NsUser nsUser, List<Student> students) {
        if (!sessionStatus.isEnrolling()) {
            throw new CannotEnrollException();
        }
        Student student = new Student(nsUser.getId(), this.id);
        enrollment.enroll(student, students);
        return student;
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
