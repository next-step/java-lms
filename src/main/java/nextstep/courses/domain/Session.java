package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.awt.*;
import java.time.LocalDateTime;

public class Session {
    private SessionInfo sessionInfo;

    private SessionStatus sessionStatus;

    private Charge charge;

    private Enrollment enrollment;

    public Session(String sessionName, LocalDateTime startDate, LocalDateTime endDate, Image image,
                   SessionStatus sessionStatus, ChargeStatus chargeStatus, int price, int maxSize) {

        this(new SessionInfo(sessionName, new SessionPeriod(startDate, endDate), image),
                sessionStatus,
                new Charge(chargeStatus, price),
                new Enrollment(chargeStatus, maxSize));
    }

    public Session(String sessionName, SessionPeriod sessionPeriod, Image image,
                   SessionStatus sessionStatus, Charge charge,
                   int enrollmentMaxSize) {
        this(new SessionInfo(sessionName, sessionPeriod, image),
                sessionStatus, charge,
                new Enrollment(charge, enrollmentMaxSize));
    }

    private Session(SessionInfo sessionInfo, SessionStatus sessionStatus, Charge charge, Enrollment enrollment) {
        this.sessionInfo = sessionInfo;
        this.sessionStatus = sessionStatus;
        this.charge = charge;
        this.enrollment = enrollment;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void registration(NsUser nsUser, Long amount) {
        if (!sessionStatus.equals(SessionStatus.RECRUITING)) {
            throw new IllegalArgumentException("'모집중'일 때만 강의 수강신청이 가능합니다.");
        }

        if (!(charge.getPrice() == amount)) {
            throw new IllegalArgumentException("수강료와 결제 금액이 같아야 합니다.");
        }

        enrollment.add(sessionStatus, nsUser);
    }
}
