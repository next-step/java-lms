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

    private Session(int maxCapacityOfStudents, LocalDate startDate, LocalDate endDate) {
        this(null, PaymentType.FREE, SessionState.PREPARING, maxCapacityOfStudents, startDate, endDate);
    }

    private Session(Image coverImage, PaymentType paymentType, SessionState state, int maxCapacityOfStudents, LocalDate startDate, LocalDate endDate) {
        this.coverImage = coverImage;
        this.paymentType = paymentType;
        this.state = state;
        this.maxCapacityOfStudents = maxCapacityOfStudents;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Session createFreeSession(int maxCapacityOfStudents, LocalDate startDate, LocalDate endDate) {
        return new Session(null, PaymentType.FREE, SessionState.PREPARING, maxCapacityOfStudents, startDate, endDate);
    }

    public static Session createSession(Image coverImage, PaymentType paymentType, SessionState state, int maxCapacityOfStudents, LocalDate startDate, LocalDate endDate) {
        return new Session(coverImage, paymentType, state, maxCapacityOfStudents, startDate, endDate);
    }


    public void registerSession(NsUser student) throws CannotRegisterSessionException {
        valid(1);
        this.students.add(student);
    }

    public void registerSessionAll(List<NsUser> students) throws CannotRegisterSessionException {
        valid(students.size());
        this.students.addAll(students);
    }

    private void valid(int countOfPerson) throws CannotRegisterSessionException {
        validStartDate();
        validStatus();
        validCapacity(countOfPerson);
    }

    private void validStartDate() throws CannotRegisterSessionException {
        LocalDate now = LocalDate.now();
        if (now.isAfter(this.startDate)) {
            throw new CannotRegisterSessionException("이미 시작한 강의입니다.");
        }
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

}
