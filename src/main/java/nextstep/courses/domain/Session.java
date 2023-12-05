package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.Set;

public class Session {

    private Period period;
    private SessionStatus status;
    private Students students;
    private SessionType sessionType;
    private SessionImage sessionImage;

    public Session() {
    }

    public Session(SessionImage image) throws PeriodException {
        this(new Period(LocalDate.now(), LocalDate.now().plusDays(1L)), SessionStatus.WAITING, new Students(), new SessionType(), image);
    }
    public Session(SessionStatus status) throws PeriodException {
        this(new Period(LocalDate.now(), LocalDate.now().plusDays(1L)), status, new Students(), new SessionType(), new SessionImage());
    }

    public Session(SessionStatus sessionStatus, LocalDate startDate, LocalDate endDate) throws PeriodException {
        this(new Period(startDate, endDate), sessionStatus, new Students(), new SessionType(), new SessionImage());
    }

    public Session(SessionStatus status, Set<NsUser> nsUsers, SessionType sessionType) throws PeriodException {
        this(new Period(LocalDate.now(), LocalDate.now().plusDays(1L)), status, new Students(nsUsers), sessionType, new SessionImage());
    }

    public Session(Period period, SessionStatus status, Students students, SessionType sessionType, SessionImage sessionImage) {
        this.period = period;
        this.status = status;
        this.students = students;
        this.sessionType = sessionType;
        if (sessionImage == null) {
            throw new IllegalArgumentException("이미지 정보는 반드시 담겨야 합니다");
        }
        this.sessionImage = sessionImage;
    }

    public Students register(NsUser user) throws CannotRegisterException {
        return register(user, new Payment());
    }

    public Students register(NsUser user, Payment payment) throws CannotRegisterException {
        if (!SessionStatus.isOpen(status)) {
            throw new CannotRegisterException("모집중이 아닌 경우 신청이 불가합니다");
        }

        if (!sessionType.isEqualPrice(payment)) {
            throw new CannotRegisterException("강의 금액과 일치하지 않습니다.");
        }

        students.registerSessionStudent(user, sessionType);
        return students;
    }
}
