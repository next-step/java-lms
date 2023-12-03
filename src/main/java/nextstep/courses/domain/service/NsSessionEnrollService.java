package nextstep.courses.domain.service;

import nextstep.courses.domain.entity.Enrollment;
import nextstep.courses.domain.entity.NsCourse;
import nextstep.courses.domain.entity.NsSession;
import nextstep.courses.domain.field.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class NsSessionEnrollService {

    private final NsCourse nsCourse;

    public NsSessionEnrollService() {
        this.nsCourse = NsCourse.DEFAULT_COURSE;
    }

    public Enrollment enroll(NsSession session, NsUser user, Payment payment) {
        Enrollment enrollment = new Enrollment();
        if (isPaid(session)) {
            enrollment = register(session, user, payment);
        }

        if (isFree(session)) {
            enrollment = new Enrollment(nsCourse, user, session);
        }

        return enrollment;
    }

    private boolean isPaid(NsSession session) {
        return SessionType.PAID.equals(session.getSessionType());
    }

    private boolean isFree(NsSession session) {
        return SessionType.FREE.equals(session.getSessionType());
    }

    private Enrollment register(NsSession session, NsUser user, Payment payment) {
        if (session.available(payment)) {
            session.decreaseQuota();

            return new Enrollment(nsCourse, user, session);
        }

        throw new IllegalArgumentException("신청인원을 초과하거나, 수강료와 결제내역이 일치하지 않습니다");
    }
}
