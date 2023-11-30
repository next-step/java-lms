package nextstep.courses.domain.session;

import nextstep.courses.domain.cource.Image;
import nextstep.courses.exception.session.EnrollmentMaxExceededException;
import nextstep.courses.exception.session.InvalidSessionStateException;
import nextstep.courses.type.SessionState;
import nextstep.courses.type.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private Period period;

    private Image image;

    private SessionType sessionType;
    private SessionState sessionState;

    private Long amount;
    private Long enrollmentMax;

    /**
     * @Todo 일급컬렉션
     */
    private Students students;

    public Session(Period period, Image image, SessionType sessionType, SessionState sessionState, Long amount, Long enrollmentMax, List<NsUser> students) {
        this.period = period;
        this.image = image;
        this.sessionType = sessionType;
        this.sessionState = sessionState;
        this.amount = amount;
        this.enrollmentMax = enrollmentMax;
        this.students = Students.of(students);
    }

    public static Session ofFree(Period period, Image image){
        return new Session(period, image, SessionType.FREE, SessionState.PREPARING, null, null, new ArrayList<>());
    }

    public static Session ofPaid(Period period, Image image, long amount, long enrollmentMax){
        return new Session(period, image, SessionType.PAID, SessionState.PREPARING, amount, enrollmentMax, new ArrayList<>());
    }

    public void enroll(NsUser student, Payment payment) {
        validateEnroll(payment);
        addStudents(student);
    }

    private void validateEnroll(Payment payment) {
        if (!SessionState.recruiting(sessionState)) {
            throw new InvalidSessionStateException("현재 강의 모집중이 아닙니다.");
        }

        if (!validateEnrollmentMax()) {
            throw new EnrollmentMaxExceededException("최대 수강 인원을 초과하였습니다.");
        }

        if(SessionType.paid(sessionType)){
            payment.complete(amount);
        }
    }

    private boolean validateEnrollmentMax() {
        return students.size() < enrollmentMax;
    }

    private void addStudents(NsUser student) {
        students.add(student);
    }

    public void preparing() {
        this.sessionState = SessionState.PREPARING;
    }

    public void recruiting() {
        this.sessionState = SessionState.RECRUITING;
    }

    public void end() {
        this.sessionState = SessionState.END;
    }
}
