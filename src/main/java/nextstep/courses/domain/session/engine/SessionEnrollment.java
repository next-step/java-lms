package nextstep.courses.domain.session.engine;

import nextstep.courses.domain.session.Students;
import nextstep.payments.domain.Payment;

public interface SessionEnrollment {

    void satisfy(Students students, Payment payment);

    void satisfyStatus();

    void satisfyCapacity(Students students);

    void satisfyFee(Payment payment);

}
