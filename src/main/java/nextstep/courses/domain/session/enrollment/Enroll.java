package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.Student;
import nextstep.courses.domain.session.Students;
import nextstep.courses.exception.session.InvalidProgressStateException;
import nextstep.courses.type.ProgressState;
import nextstep.courses.type.RecruitState;
import nextstep.courses.type.SessionType;
import nextstep.payments.domain.Payment;

public interface Enroll {

    void validate(Enrollment enrollment, Students students, Payment payment);

    static Enroll from(SessionType sessionType) {
        if (sessionType.isPaid()) {
            return fromPaid();
        }

        return fromFree();
    }

    static PaidEnroll fromPaid() {
        return PaidEnroll.from();
    }

    static FreeEnroll fromFree() {
        return FreeEnroll.from();
    }

    default void validateRecruiting(ProgressState progressState, RecruitState recruitState) {
        if (!progressState.ongoing()) {
            throw new InvalidProgressStateException("현재 강의 모집중이 아닙니다.");
        }

        if (!recruitState.recruiting()) {
            throw new InvalidProgressStateException("현재 강의 모집중이 아닙니다.");
        }
    }

    default void enroll(Enrollment enrollment, Students students, Student student, Payment payment) {
        validateRecruiting(enrollment.progressState(), enrollment.recruitState());
        validate(enrollment, students, payment);
        students.add(student);
    }
}
