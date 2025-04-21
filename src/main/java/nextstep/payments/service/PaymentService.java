package nextstep.payments.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.service.SessionService;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentEntityUserMap;
import nextstep.payments.domain.PaymentRepository;
import nextstep.payments.domain.Payments;
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
    public boolean approve(long paymentId, String approverId) throws IOException {
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId);
        String applicantUserId = paymentEntity.getUserId().toString();

        if (userService.canApprove(approverId, paymentEntity.getUserId().toString())) {
            Long sessionId = paymentEntity.getSessionId();
            Session session = sessionService.createSession(sessionId);
            NsUser user = userService.getUser(applicantUserId);
            createPayment(paymentEntity, session, user).approve();
            return true;
        }

        return false;
    }

    @Transactional
    public boolean cancel(long paymentId, String approverId) throws IOException {
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId);
        String applicantUserId = paymentEntity.getUserId().toString();

        if (userService.canCancel(approverId, paymentEntity.getUserId().toString())) {
            Long sessionId = paymentEntity.getSessionId();
            Session session = sessionService.createSession(sessionId);
            createPayment(paymentEntity, session, userService.getUser(applicantUserId)).cancel();
            return true;
        }

        return false;
    }

    public void savePayment(Payment payment) {
        paymentRepository.save(paymentFactory.createPaymentEntity(payment));
    }

    public Payment createPayment(PaymentEntity paymentEntity, Session session, NsUser nsUser) {
        return paymentFactory.createPayment(paymentEntity, session, nsUser);
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
