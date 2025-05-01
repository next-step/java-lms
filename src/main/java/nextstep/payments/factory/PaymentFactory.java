package nextstep.payments.factory;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentEntityUserMap;
import nextstep.payments.domain.PaymentStatus;
import nextstep.payments.domain.Payments;
import nextstep.payments.entity.PaymentEntity;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentFactory {

    public Payment createPayment(PaymentEntity paymentEntity, Session session, NsUser nsUser) {
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

    public Payments createPayments(Session session, PaymentEntityUserMap paymentEntityNsUserMap) {
        List<Payment> paymentList = paymentEntityNsUserMap.entrySet().stream()
            .map(entry -> {
                PaymentEntity paymentEntity = entry.getKey();
                NsUser user = entry.getValue();
                return createPayment(paymentEntity, session, user);
            }).collect(Collectors.toList());

        return new Payments(paymentList);
    }

    public PaymentEntity createPaymentEntity(Payment payment) {
        return PaymentEntity.builder()
            .id(payment.id())
            .userId(payment.getUser().getUserId())
            .sessionId(payment.getSession().id())
            .amount(payment.getAmount())
            .createdAt(payment.getCreatedAt())
            .updatedAt(payment.getUpdatedAt())
            .deleted(payment.isDeleted())
            .status(payment.getStatus().getStatus())
            .build();
    }
}
