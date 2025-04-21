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
    private int createPaymentCalled = 0;
    private int createPaymentsCalled = 0;

    public TestPaymentFactory(Payment createPaymentResult) {
        this(createPaymentResult, null);
    }

    public TestPaymentFactory(Payments createPaymentsResult) {
        this(null, createPaymentsResult);
    }

    public TestPaymentFactory(Payment createPaymentResult, Payments createPaymentsResult) {
        this.createPaymentResult = createPaymentResult;
        this.createPaymentsResult = createPaymentsResult;
    }

    @Override
    public Payment createPayment(PaymentEntity paymentEntity, Session session, NsUser nsUser) {
        createPaymentCalled++;
        return createPaymentResult;
    }

    @Override
    public Payments createPayments(Session session, PaymentEntityUserMap paymentEntityNsUserMap) {
        createPaymentsCalled++;
        return createPaymentsResult;
    }

    public int getCreatePaymentsCalled() {
        return createPaymentsCalled;
    }

    public int getCreatePaymentCalled() {
        return createPaymentCalled;
    }
}
