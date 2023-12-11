package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.Student;
import nextstep.courses.exception.session.InvalidProgressStateException;
import nextstep.courses.type.SessionType;
import nextstep.payments.domain.Payment;

public interface Enrollment {

    void enroll(Session session, Student student, Payment payment);

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
        if (!session.progressState().ongoing()) {
            throw new InvalidProgressStateException("현재 강의 모집중이 아닙니다.");
        }

        if (!session.recruitState().recruiting()) {
            throw new InvalidProgressStateException("현재 강의 모집중이 아닙니다.");
        }
    }
}
