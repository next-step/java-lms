package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;

public interface Enrollment {

    void enroll(Session session, Payment payment);

    boolean checkRegistrationConditions(Payment payment);

    boolean isRegistrationPossible();

    boolean isPaymentAmountSameTuitionFee(Payment payment);
}
