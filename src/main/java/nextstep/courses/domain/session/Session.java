package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Session {
    private final Students students = new Students();
    private final Period period;
    private final CoverImage coverImage;
    private final SessionStatus sessionStatus;
    private final EnrollmentPolicy enrollmentPolicy;

    public Session(Period period, CoverImage coverImage, SessionStatus sessionStatus,
        EnrollmentPolicy enrollmentPolicy) {
        this.period = period;
        this.coverImage = coverImage;
        this.sessionStatus = sessionStatus;
        this.enrollmentPolicy = enrollmentPolicy;
    }

    public void enroll(NsUser user) {
        enroll(user, null);
    }

    public void enroll(NsUser user, Payment payment) {
        validateRecruiting();
        enrollmentPolicy.validateEnrollment(students, payment);
        students.add(user);
    }

    private void validateRecruiting() {
        if (!sessionStatus.isEnrollable()) {
            throw new IllegalStateException();
        }
    }
}
