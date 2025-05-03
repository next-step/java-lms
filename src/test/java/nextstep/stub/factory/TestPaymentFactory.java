package nextstep.stub.factory;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentEntityUserMap;
import nextstep.payments.domain.Payments;
import nextstep.payments.entity.PaymentEntity;
import nextstep.payments.factory.PaymentFactory;
import nextstep.users.domain.NsUser;

public class TestPaymentFactory extends PaymentFactory {
    private final Payment createPaymentResult;
    private final Payments createPaymentsResult;
    private final PaymentEntity createPaymentEntityResult;

    public TestPaymentFactory() {
        this(null, null, null);
    }

    public TestPaymentFactory(Payment createPaymentResult) {
        this(createPaymentResult, null, null);
    }

    public TestPaymentFactory(Payments createPaymentsResult) {
        this(null, createPaymentsResult, null);
    }

    public TestPaymentFactory(Payment createPaymentResult, Payments createPaymentsResult, PaymentEntity createPaymentEntityResult) {
        this.createPaymentResult = createPaymentResult;
        this.createPaymentsResult = createPaymentsResult;
        this.createPaymentEntityResult = createPaymentEntityResult;
    }

    @Override
    public Payment createPayment(PaymentEntity paymentEntity, Session session, NsUser nsUser) {
        return createPaymentResult;
    }

    @Override
    public Payments createPayments(Session session, PaymentEntityUserMap paymentEntityNsUserMap) {
        return createPaymentsResult;
    }

    @Override
    public PaymentEntity createPaymentEntity(Payment payment) {
        return createPaymentEntityResult;
    }
}
