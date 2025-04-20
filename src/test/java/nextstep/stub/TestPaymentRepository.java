package nextstep.stub;

import nextstep.payments.domain.PaymentRepository;
import nextstep.payments.entity.PaymentEntity;

import java.util.List;

public class TestPaymentRepository implements PaymentRepository {
    private final long saveResult;
    private final List<PaymentEntity> findBySessionResult;
    private int saveCalled = 0;
    private int findBySessionCalled = 0;

    public TestPaymentRepository(long saveResult, List<PaymentEntity> findBySessionResult) {
        this.saveResult = saveResult;
        this.findBySessionResult = findBySessionResult;
    }

    @Override
    public long save(PaymentEntity paymentEntity) {
        saveCalled++;
        return saveResult;
    }

    @Override
    public List<PaymentEntity> findBySession(Long sessionId) {
        findBySessionCalled++;
        return findBySessionResult;
    }

    public int getSaveCalled() {
        return saveCalled;
    }

    public int getFindBySessionCalled() {
        return findBySessionCalled;
    }
}
