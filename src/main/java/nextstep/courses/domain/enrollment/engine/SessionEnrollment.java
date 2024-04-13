package nextstep.courses.domain.enrollment.engine;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public interface SessionEnrollment {

    void enroll(NsUser nsUser, Payment payment);

    default void satisfyEnrollment(Payment payment) {
        satisfyStatus();
        satisfyCapacity();
        satisfyFee(payment);
    }

    void satisfyStatus();

    void satisfyCapacity();

    void satisfyFee(Payment payment);

}
