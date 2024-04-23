package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.session.SessionStatus;
import nextstep.payments.domain.Payment;

public interface Enrollment {

    default boolean isRecruitmentOpen(SessionStatus sessionStatus) {
        return SessionStatus.RECRUITING == sessionStatus;
    }

    boolean isPaymentAmountSameTuitionFee(Payment payment);
}
