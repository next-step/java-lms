package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

public interface SessionOperations {

    void validateNotFull(Enrollments enrollments);
    void validatePaymentAmount(Payment payment);
    void validateSessionStatus();


}
