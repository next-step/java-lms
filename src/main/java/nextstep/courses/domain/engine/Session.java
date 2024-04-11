package nextstep.courses.domain.engine;

import nextstep.courses.domain.enrollment.Student;
import nextstep.payments.domain.Payment;

@FunctionalInterface
public interface Session {

    void enroll(Student student, Payment payment);

}
