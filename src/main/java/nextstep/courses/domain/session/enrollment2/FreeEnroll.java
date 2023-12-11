package nextstep.courses.domain.session.enrollment2;

import nextstep.courses.domain.session.Students;
import nextstep.payments.domain.Payment;

public class FreeEnroll implements Enroll {

    private FreeEnroll() {
    }

    public static FreeEnroll from() {
        return new FreeEnroll();
    }

    @Override
    public void validate(EnrollmentInfo enrollmentInfo, Students students, Payment payment) {

    }
}
