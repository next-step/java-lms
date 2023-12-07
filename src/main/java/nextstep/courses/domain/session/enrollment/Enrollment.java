package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.courses.exception.session.InvalidSessionStateException;
import nextstep.courses.type.SessionState;
import nextstep.courses.type.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public interface Enrollment {

    void enroll(Session session, NsUser student, Payment payment);

    static Enrollment from(SessionType sessionType) {
        if (sessionType == SessionType.PAID) {
            return fromPaid();
        }

        return fromFree();
    }

    static PaidEnrollment fromPaid() {
        return PaidEnrollment.from();
    }

    static FreeEnrollment fromFree() {
        return FreeEnrollment.from();
    }

    default void noRecruiting(Session session) {
        if (!SessionState.recruiting(session.sessionState())) {
            throw new InvalidSessionStateException("현재 강의 모집중이 아닙니다.");
        }
    }
}
