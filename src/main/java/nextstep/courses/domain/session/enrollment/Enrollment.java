package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.student.Student;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public interface Enrollment {

    Student enroll(NsUser nsUser, Payment payment);

    boolean checkRegistrationConditions(Payment payment);

    boolean isRegistrationPossible();

    boolean isPaymentAmountSameTuitionFee(Payment payment);
}
