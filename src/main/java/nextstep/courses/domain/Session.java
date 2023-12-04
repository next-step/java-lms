package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private Period period;
    private SessionStatus status;
    private List<NsUser> registeredUser;

    private Students students;

    private SessionType sessionType;

    public Session() {
    }

    public Session(SessionStatus status) {
        this.status = status;
        this.sessionType = new SessionType();
        this.registeredUser = new ArrayList<>();
        this.students = new Students();
    }

    public Session(SessionStatus sessionStatus, LocalDate startDate, LocalDate endDate) {
        this.status = sessionStatus;
        this.period = new Period(startDate, endDate);
    }

    public Session(SessionStatus status, List<NsUser> nsUsers, SessionType sessionType) {
        this.status = status;
        this.registeredUser = nsUsers;
        this.students = new Students(nsUsers);
        this.sessionType = sessionType;
    }

    public Students register(NsUser user, Payment payment) {
        if (!sessionType.isEqualPrice(payment)) {
            throw new IllegalArgumentException();
        }
        students.registerSessionStudent(user);
        return students;
    }

    public List<NsUser> register(NsUser user) throws CannotRegisterException {
        if (!SessionStatus.OPEN.equals(status)) {
            throw new CannotRegisterException("모집중이 아닌 경우 신청이 불가합니다");
        }
        if (!sessionType.isMaxCapacity(registeredUser)) {
            throw new IllegalArgumentException();
        }
        registeredUser.add(user);
        return registeredUser;
    }
}
