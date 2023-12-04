package nextstep.courses.domain.session;

import nextstep.courses.domain.cource.Image;
import nextstep.courses.exception.session.EnrollmentMaxExceededException;
import nextstep.courses.exception.session.InvalidSessionStateException;
import nextstep.courses.type.SessionState;
import nextstep.courses.type.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;

public class Session {
    private final Period period;

    private final Image image;

    private final SessionType sessionType;
    private final SessionState sessionState;

    private final Long amount;
    private final Long enrollmentMax;

    private final Students students;

    public Session(Period period, Image image, SessionType sessionType, SessionState sessionState, Long amount, Long enrollmentMax, Students students) {
        this.period = period;
        this.image = image;
        this.sessionType = sessionType;
        this.sessionState = sessionState;
        this.amount = amount;
        this.enrollmentMax = enrollmentMax;
        this.students = students;
    }

    public static Session ofFree(Period period, Image image){
        return new Session(period, image, SessionType.FREE, SessionState.PREPARING, null, null, Students.of(new ArrayList<>()));
    }

    public static Session ofPaid(Period period, Image image, long amount, long enrollmentMax){
        return new Session(period, image, SessionType.PAID, SessionState.PREPARING, amount, enrollmentMax, Students.of(new ArrayList<>()));
    }

    public void enroll(NsUser student, Payment payment) {
        validateEnroll(payment);
        addStudents(student);
    }

    private void validateEnroll(Payment payment) {
        if (!SessionState.recruiting(sessionState)) {
            throw new InvalidSessionStateException("현재 강의 모집중이 아닙니다.");
        }

        if (!isFullEnrollment()) {
            throw new EnrollmentMaxExceededException("최대 수강 인원을 초과하였습니다.");
        }

        if(SessionType.paid(sessionType)){
            payment.complete(amount);
        }
    }

    private boolean isFullEnrollment() {
        return students.size() < enrollmentMax;
    }

    private void addStudents(NsUser student) {
        students.add(student);
    }

    public Session preparing() {
        return new Session(period, image, sessionType, SessionState.PREPARING, amount, enrollmentMax, students);
    }

    public Session recruiting() {
        return new Session(period, image, sessionType, SessionState.RECRUITING, amount, enrollmentMax, students);
    }

    public Session end() {
        return new Session(period, image, sessionType, SessionState.END, amount, enrollmentMax, students);
    }
}
