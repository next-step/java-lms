package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private LocalDate startDate;
    private LocalDate endDate;
    private SessionStatus status;
    private List<NsUser> registeredUser;

    private SessionType sessionType;

    public Session() {
    }

    public Session(SessionStatus status) {
        this.status = status;
        this.sessionType = new SessionType();
        this.registeredUser = new ArrayList<>();
    }

    public Session(SessionStatus sessionStatus, LocalDate startDate, LocalDate endDate) {
        this.status = sessionStatus;
        if(startDate.isAfter(endDate)) {
            throw new IllegalArgumentException();
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Session(SessionStatus status, List<NsUser> nsUsers, SessionType sessionType) {
        this.status = status;
        this.registeredUser = nsUsers;
        this.sessionType = sessionType;
    }

    public List<NsUser> register(NsUser user, Payment payment) {
        if (!sessionType.isEqualPrice(payment)) {
            throw new IllegalArgumentException();
        }
        registeredUser.add(user);
        return registeredUser;
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
