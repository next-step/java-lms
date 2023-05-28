package nextstep.courses.domain;

import nextstep.courses.CannotRegisterSessionException;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private Image coverImage;
    private PaymentType paymentType;
    private SessionState state;
    private int maxCapacityOfStudents;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<NsUser> students = new ArrayList<>();

    public Session(int maxCapacityOfStudents, LocalDate startDate, LocalDate endDate) {
        this(null, PaymentType.FREE, SessionState.PREPARING, maxCapacityOfStudents, startDate, endDate);
    }

    public Session(Image coverImage, PaymentType paymentType, SessionState state, int maxCapacityOfStudents, LocalDate startDate, LocalDate endDate) {
        this.coverImage = coverImage;
        this.paymentType = paymentType;
        this.state = state;
        this.maxCapacityOfStudents = maxCapacityOfStudents;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void registerSession(NsUser student) throws CannotRegisterSessionException {
        validStatus();
        validCapacity(1);
        this.students.add(student);
    }

    public void registerSession(List<NsUser> students) throws CannotRegisterSessionException {
        validStatus();
        validCapacity(students.size());
        this.students.addAll(students);
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

    public void preparing() {
        state = SessionState.PREPARING;
    }

    public void recruiting() {
        state = SessionState.RECRUITING;
    }

    public void close() {
        state = SessionState.CLOSED;
    }
}
