package nextstep.payments.domain;

public interface PaymentRepository {

    Payment findBySessionAndUser(Long sessionId, Long userId);
}
