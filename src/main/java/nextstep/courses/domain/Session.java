package nextstep.courses.domain;

import nextstep.courses.CannotRegisterSessionException;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.List;

public class Session {

    private Image coverImage;
    private PaymentType paymentType;
    private SessionState state;
    private int maxCapacityOfStudents;

    private SessionPeriod sessionPeriod;

    private Students students;

    private Session(int maxCapacityOfStudents, SessionPeriod sessionPeriod) {
        this(null, PaymentType.FREE, SessionState.PREPARING, maxCapacityOfStudents, sessionPeriod);
    }

    public Session(Image coverImage, PaymentType paymentType, SessionState state, int maxCapacityOfStudents, SessionPeriod sessionPeriod) {
        this.coverImage = coverImage;
        this.paymentType = paymentType;
        this.state = state;
        this.maxCapacityOfStudents = maxCapacityOfStudents;
        this.sessionPeriod = sessionPeriod;
        this.students = new Students(maxCapacityOfStudents);
    }

    public static Session createFreeSession(int maxCapacityOfStudents, SessionPeriod sessionPeriod) {
        return new Session(null, PaymentType.FREE, SessionState.PREPARING, maxCapacityOfStudents, sessionPeriod);
    }

    public static Session createSession(Image coverImage, PaymentType paymentType, SessionState state, int maxCapacityOfStudents, SessionPeriod sessionPeriod) {
        return new Session(coverImage, paymentType, state, maxCapacityOfStudents, sessionPeriod);
    }

    public void registerSession(NsUser student) throws CannotRegisterSessionException {
        this.students.register(student);
    }

    public void registerSessionAll(List<NsUser> students) throws CannotRegisterSessionException {
        valid(students.size());
        this.students.register(students);
    }

    private void valid(int countOfPerson) throws CannotRegisterSessionException {
        validStatus();
        validCapacity(countOfPerson);
    }


    private void validStatus() throws CannotRegisterSessionException {
        if (!state.canRegistering()) {
            throw new CannotRegisterSessionException("현재 모집중이 아닙니다.");
        }
    }

    private void validCapacity(int countOfPerson) throws CannotRegisterSessionException {
        if (countOfPerson >= maxCapacityOfStudents - students.size()) {
            throw new CannotRegisterSessionException("최대 수강인원을 초과했습니다.");
        }
    }

    public void nextState() {
        this.state = state.updateState();
    }

    public String getCoverImage() {
        return coverImage.getImage();
    }

    public String getPaymentType() {
        return paymentType.name();
    }

    public String getState() {
        return state.name();
    }

    public int getMaxCapacityOfStudents() {
        return maxCapacityOfStudents;
    }

    public LocalDate getStartDate() {
        return sessionPeriod.getStartDate();
    }

    public LocalDate getEndDate() {
        return sessionPeriod.getEndDate();
    }

}
