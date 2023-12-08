package nextstep.courses.domain.session;

import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.type.SessionState;
import nextstep.courses.type.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;

public class Session {
    private final Long id;

    private final SessionType sessionType;
    private final SessionState sessionState;

    private final Period period;

    private final Long amount;
    private final Long enrollmentMax;

    private Image image;

    private Students students;

    private final Enrollment enrollment;


    public Session(Long id, SessionType sessionType, SessionState sessionState, Period period, Long amount, Long enrollmentMax, Image image, Students students) {
        this.id = id;
        this.sessionType = sessionType;
        this.sessionState = sessionState;
        this.period = period;
        this.amount = amount;
        this.enrollmentMax = enrollmentMax;
        this.image = image;
        this.students = students;
        this.enrollment = Enrollment.from(sessionType);
    }

    public static Session ofFree(Period period, Image image) {
        return of(null, SessionType.FREE, SessionState.PREPARING, period, null, null, image, Students.of(new ArrayList<>()));
    }

    public static Session ofPaid(Period period, Image image, long amount, long enrollmentMax) {
        return of(null, SessionType.PAID, SessionState.PREPARING, period, amount, enrollmentMax, image, Students.of(new ArrayList<>()));
    }

    public static Session of(Long id, String sessionType, String sessionState, LocalDate startDate, LocalDate endDate, Long amount, Long enrollmentMax) {
        return of(id, SessionType.valueOf(sessionType), SessionState.valueOf(sessionState), Period.of(startDate, endDate), amount, enrollmentMax, null, null);
    }

    public static Session of(Long id, SessionType sessionType, SessionState sessionState, Period period, Long amount, Long enrollmentMax, Image image, Students students) {
        return new Session(id, sessionType, sessionState, period, amount, enrollmentMax, image, students);
    }


    public void enroll(NsUser student, Payment payment) {
        enrollment.enroll(this, student, payment);
    }

    public boolean isFullEnrollment() {
        return students.size() < enrollmentMax;
    }

    public Session preparing() {
        return of(id, sessionType, SessionState.PREPARING, period, amount, enrollmentMax, image, students);
    }

    public Session recruiting() {
        return of(id, sessionType, SessionState.RECRUITING, period, amount, enrollmentMax, image, students);
    }

    public Session end() {
        return of(id, sessionType, SessionState.END, period, amount, enrollmentMax, image, students);
    }

    public Long id() {
        return id;
    }

    public String type() {
        return sessionType.name();
    }

    public SessionState sessionState() {
        return sessionState;
    }

    public String state() {
        return sessionState.name();
    }

    public LocalDate startDate() {
        return period.startDate();
    }

    public LocalDate endDate() {
        return period.endDate();
    }

    public Long amount() {
        return amount;
    }

    public Long enrollmentMax() {
        return enrollmentMax;
    }

    public Image image() {
        return image;
    }

    public void changeImage(Image image) {
        this.image = image;
    }

    public Students students() {
        return students;
    }

    public void changeStudents(Students students) {
        this.students = students;
    }

    public void addStudent(NsUser student) {
        students.add(student);
    }


    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", sessionType=" + sessionType +
                ", sessionState=" + sessionState +
                ", period=" + period +
                ", amount=" + amount +
                ", enrollmentMax=" + enrollmentMax +
                ", image=" + image +
                ", students=" + students +
                '}';
    }
}
