package nextstep.stub.repository;

import nextstep.payments.domain.PaymentRepository;
import nextstep.payments.entity.PaymentEntity;

import java.util.List;

public class TestPaymentRepository implements PaymentRepository {
    private final long saveResult;
    private final PaymentEntity findByIdResult;
    private final List<PaymentEntity> findBySessionResult;
    private int saveCalled = 0;
    private int updateCalled = 0;

    public TestPaymentRepository(long saveResult) {
        this(saveResult, null, List.of());
    }

    public TestPaymentRepository(long saveResult, PaymentEntity findByIdResult) {
        this(saveResult, findByIdResult, null);
    }

    public TestPaymentRepository(long saveResult, PaymentEntity findByIdResult, List<PaymentEntity> findBySessionResult) {
        this.saveResult = saveResult;
        this.findByIdResult = findByIdResult;
        this.findBySessionResult = findBySessionResult;
    }

    @Override
    public long save(PaymentEntity paymentEntity) {
        saveCalled++;
        return saveResult;
    }

    @Override
    public PaymentEntity findById(Long paymentId) {
        return findByIdResult;
    }

    @Override
    public List<PaymentEntity> findBySession(Long sessionId) {
        return findBySessionResult;
    }

    @Override
    public void updateStatus(Long paymentId, String status) {
        updateCalled++;
    }

    public int getSaveCalled() {
        return saveCalled;
    }

    public int getUpdateCalled() {
        return updateCalled;
    }
}
