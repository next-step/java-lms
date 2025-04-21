package nextstep.payments.factory;

import nextstep.payments.domain.Payment;
import nextstep.payments.entity.PaymentEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentEntityFactory {

    public PaymentEntity create(Payment payment) {
        return PaymentEntity.builder()
            .id(payment.id())
            .userId(payment.getUser().id())
            .sessionId(payment.getSession().id())
            .amount(payment.getAmount())
            .createdAt(payment.getCreatedAt())
            .updatedAt(payment.getUpdatedAt())
            .deleted(payment.isDeleted())
            .status(payment.getStatus().getStatus())
            .build();
    }
}
