package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.Students;
import nextstep.payments.domain.Payment;

public class FreeEnroll implements Enroll {

    private FreeEnroll() {
    }

    public static FreeEnroll from() {
        return new FreeEnroll();
    }

    @Override
    public void validate(Enrollment enrollment, Students students, Payment payment) {

    }
}
