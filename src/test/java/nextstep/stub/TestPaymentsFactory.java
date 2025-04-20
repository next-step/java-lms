package nextstep.stub;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.PaymentEntityUserMap;
import nextstep.payments.domain.Payments;
import nextstep.payments.factory.PaymentFactory;
import nextstep.payments.factory.PaymentsFactory;

public class TestPaymentsFactory extends PaymentsFactory {
    private final Payments createResult;
    private int createCalled = 0;

    public TestPaymentsFactory(PaymentFactory paymentFactory, Payments createResult) {
        super(paymentFactory);
        this.createResult = createResult;
    }

    @Override
    public Payments create(Session session, PaymentEntityUserMap paymentEntityNsUserMap) {
        createCalled++;
        return createResult;
    }

    public int getCreateCalled() {
        return createCalled;
    }
}
