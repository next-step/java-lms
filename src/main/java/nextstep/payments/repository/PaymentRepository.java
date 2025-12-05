package nextstep.payments.repository;

import nextstep.payments.domain.Payment;

public interface PaymentRepository {

    int save(Payment payment);
}
