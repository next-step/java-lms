package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.sessionuser.SessionUser;
import nextstep.courses.domain.sessionuser.SessionUsers;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Enrollment {

    private SessionUsers sessionUsers;

    private Enrollment(Payment payment, NsUser nsUser, Session session) {
        validateSessionStatus(session);
        validateAmount(payment, session);
        sessionUsers.addSessionUser(new SessionUser(nsUser, session));
    }

    public Enrollment(Session session) {
        this(null, null, session);
    }

    public static Enrollment enrollFreeSession(Session session, NsUser user) {
        return new Enrollment(null, user, session);
    }

    public static Enrollment enrollPaidSession(Payment payment, Session session, NsUser user) {
        return new Enrollment(payment, user, session);
    }

    private void validateSessionStatus(Session session) {
        if (!session.isOpened()) {
            throw new IllegalArgumentException("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.");
        }
    }

    private void validateAmount(Payment payment, Session session) {
        if (!session.isEnrollmentAmountValid(payment)) {
            throw new IllegalArgumentException("결제한 금액과 수강료가 일치하지 않습니다.");
        }
    }
}
