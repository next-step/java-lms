package nextstep.courses.domain.enrollment.engine;

import nextstep.courses.domain.enrollment.Student;
import nextstep.payments.domain.Payment;

public interface SessionEnrollment {

    void satisfy(Student student, Payment payment);

}
