package nextstep.courses.domain.session.engine;

import nextstep.courses.domain.session.Student;
import nextstep.payments.domain.Payment;

@FunctionalInterface
public interface Session {

    void enroll(Student student, Payment payment);

}
