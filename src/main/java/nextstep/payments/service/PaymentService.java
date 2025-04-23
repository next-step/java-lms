package nextstep.payments.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.service.SessionService;
import nextstep.payments.domain.*;
import nextstep.payments.entity.PaymentEntity;
import nextstep.payments.factory.PaymentFactory;
import nextstep.users.domain.NsUser;
import nextstep.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentFactory paymentFactory;
    private final UserService userService;
    private final SessionService sessionService;

    public PaymentService(
        PaymentRepository paymentRepository,
        PaymentFactory paymentFactory,
        SessionService sessionService,
        UserService userService
    ) {
        this.paymentRepository = paymentRepository;
        this.paymentFactory = paymentFactory;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    public Payment payment(String id) {
        // PG사 API를 통해 id에 해당하는 결제 정보를 반환
        return new Payment();
    }

    public boolean enroll(String newPaymentId, long sessionId) throws IOException {
        PaymentEntityUserMap paymentEntityUserMap = getPaymentEntityUserMapForSession(sessionId);
        Session session = sessionService.createSession(sessionId);
        Payments payments = paymentFactory.createPayments(session, paymentEntityUserMap);
        Payment newPayment = payment(newPaymentId);

        if (payments.canEnroll(session, newPayment)) {
            savePayment(newPayment);
            return true;
        }

        return false;
    }

    @Transactional
    public boolean approve(long paymentId, String approverId) {
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId);

        if (userService.canApprove(approverId, paymentEntity.getUserId().toString())) {
            updatePaymentStatus(paymentId, PaymentStatus.APPROVED);
            return true;
        }

        return false;
    }

    @Transactional
    public boolean cancel(long paymentId, String approverId) {
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId);

        if (userService.canCancel(approverId, paymentEntity.getUserId().toString())) {
            updatePaymentStatus(paymentId, PaymentStatus.CANCELED);
            return true;
        }

        return false;
    }

    public void savePayment(Payment payment) {
        paymentRepository.save(paymentFactory.createPaymentEntity(payment));
    }

    public void updatePaymentStatus(long paymentId, PaymentStatus status) {
        paymentRepository.updateStatus(paymentId, status.getStatus());
    }

    private PaymentEntityUserMap getPaymentEntityUserMapForSession(long sessionId) {
        List<PaymentEntity> paymentEntities = paymentRepository.findBySession(sessionId);
        List<String> userIds = paymentEntities.stream()
            .map(paymentEntity -> paymentEntity.getUserId().toString())
            .collect(Collectors.toList());
        List<NsUser> users = userService.getUsers(userIds);

        Map<PaymentEntity, NsUser> map = IntStream.range(0, paymentEntities.size())
            .boxed()
            .collect(Collectors.toMap(paymentEntities::get, users::get));

        return new PaymentEntityUserMap(map);
    }
}
