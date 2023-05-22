package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class Session {
    private final Long id;

    private final  SessionInfo sessionInfo;

    private final  Charge charge;

    private final  Enrollment enrollment;

    private final  SessionPeriod sessionPeriod;

    public Session(String title, Long creatorId, String coverImage,
                   ChargeStatus chargeStatus, int price,
                   int capacity, int sizeOfStudents, SessionStatus sessionStatus,
                   LocalDate startDate, LocalDate endDate) {
        this(0L,
                new SessionInfo(title, creatorId, coverImage),
                new Charge(chargeStatus, price),
                new Enrollment(capacity, sizeOfStudents, sessionStatus),
                new SessionPeriod(startDate, endDate));
    }

    public Session(Long id, String title, Long creatorId, String coverImage,
                   ChargeStatus chargeStatus, int price,
                   int capacity, int sizeOfStudents, SessionStatus sessionStatus,
                   LocalDate startDate, LocalDate endDate) {
        this(id,
                new SessionInfo(title, creatorId, coverImage),
                new Charge(chargeStatus, price),
                new Enrollment(capacity, sizeOfStudents, sessionStatus),
                new SessionPeriod(startDate, endDate));
    }

    public Session(Long id, SessionInfo sessionInfo, Charge charge, Enrollment enrollment, SessionPeriod sessionPeriod) {
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.charge = charge;
        this.enrollment = enrollment;
        this.sessionPeriod = sessionPeriod;
    }

    public Enrollment enroll(NsUser student, int paidPrice) {
        return enrollment.enroll();
    }
}
