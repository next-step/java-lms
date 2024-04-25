package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.enrollment.count.engine.EnrollmentCount;
import nextstep.courses.domain.session.enrollment.state.SessionState;
import nextstep.courses.domain.student.Student;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public interface Enrollment {

    Student enroll(NsUser nsUser, Payment payment);

    boolean checkRegistrationConditions(Payment payment);

    boolean isRegistrationPossible();

    boolean isPaymentAmountSameTuitionFee(Payment payment);

    EnrollmentCount getEnrollmentCount();

    SessionState getSessionState();

    int getTuitionFee();

    String getFeeType();
}
