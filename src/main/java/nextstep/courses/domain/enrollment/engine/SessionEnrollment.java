package nextstep.courses.domain.enrollment.engine;

import nextstep.courses.domain.enrollment.Student;
import nextstep.payments.domain.Payment;

public interface SessionEnrollment {

    void enroll(Student student, Payment payment);

    default void satisfyEnrollment(Payment payment) {
        satisfyStatus();
        satisfyCapacity();
        satisfyFee(payment);
    }

    void satisfyStatus();

    void satisfyCapacity();

    void satisfyFee(Payment payment);

}
