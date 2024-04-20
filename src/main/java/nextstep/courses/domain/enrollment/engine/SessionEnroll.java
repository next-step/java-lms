package nextstep.courses.domain.enrollment.engine;

import nextstep.courses.domain.student.SessionStudent;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public interface SessionEnroll {

    SessionStudent enroll(Long sessionId, NsUser nsUser, Payment payment);

    default void satisfyEnrollment(Payment payment) {
        satisfyStatus();
        satisfyCapacity();
        satisfyFee(payment);
    }

    void satisfyStatus();

    void satisfyCapacity();

    void satisfyFee(Payment payment);

}
