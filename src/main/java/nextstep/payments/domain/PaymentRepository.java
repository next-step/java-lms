package nextstep.payments.domain;

import nextstep.payments.entity.PaymentEntity;

import java.util.List;

public interface PaymentRepository {
    long save(PaymentEntity paymentEntity);

    PaymentEntity findById(Long paymentId);

    List<PaymentEntity> findBySession(Long sessionId);

    void updateStatus(Long paymentId, String status);
}
