package nextstep.stub.factory;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.payments.entity.PaymentEntity;
import nextstep.payments.factory.PaymentFactory;
import nextstep.users.domain.NsUser;

public class TestPaymentFactory extends PaymentFactory {
    private final Payment createResult;
    private int createCalled = 0;

    public TestPaymentFactory() {
        this(null);
    }

    public TestPaymentFactory(Payment createResult) {
        this.createResult = createResult;
    }

    @Override
    public Payment create(PaymentEntity paymentEntity, Session session, NsUser nsUser) {
        createCalled++;
        return createResult;
    }

    public int getCreateCalled() {
        return createCalled;
    }
}
