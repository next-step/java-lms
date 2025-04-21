package nextstep.payments.factory;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentStatus;
import nextstep.payments.entity.PaymentEntity;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Component;

@Component
public class PaymentFactory {

    public Payment create(PaymentEntity paymentEntity, Session session, NsUser nsUser) {
        return new Payment(
            paymentEntity.getId(),
            paymentEntity.isDeleted(),
            paymentEntity.getCreatedAt(),
            paymentEntity.getUpdatedAt(),
            session,
            nsUser,
            paymentEntity.getAmount(),
            PaymentStatus.fromString(paymentEntity.getStatus())
        );
    }
}
