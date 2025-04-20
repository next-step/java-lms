package nextstep.payments.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.factory.SessionFactory;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentEntityUserMap;
import nextstep.payments.domain.PaymentRepository;
import nextstep.payments.domain.Payments;
import nextstep.payments.factory.PaymentsFactory;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
public class PaymentService {

    private final SessionRepository sessionRepository;
    private final SessionImageRepository sessionImageRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final SessionFactory sessionFactory;
    private final PaymentsFactory paymentsFactory;

    public PaymentService(
        SessionRepository sessionRepository,
        SessionImageRepository sessionImageRepository,
        PaymentRepository paymentRepository,
        UserRepository userRepository,
        SessionFactory sessionFactory,
        PaymentsFactory paymentsFactory
    ) {
        this.sessionRepository = sessionRepository;
        this.sessionImageRepository = sessionImageRepository;
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.sessionFactory = sessionFactory;
        this.paymentsFactory = paymentsFactory;
    }

    public Payment payment(String id) {
        // PG사 API를 통해 id에 해당하는 결제 정보를 반환
        return new Payment();
    }

    public boolean save(String newPaymentId, long sessionId) throws IOException {
        PaymentEntityUserMap paymentEntityUserMap = new PaymentEntityUserMap();
        paymentRepository.findBySession(sessionId).forEach(paymentEntity -> {
            NsUser user = userRepository.findByUserId(paymentEntity.getUserId().toString())
                .orElseThrow(() -> new NoSuchElementException("User not found for ID: " + paymentEntity.getUserId()));
            paymentEntityUserMap.add(paymentEntity, user);
        });

        Session session = sessionFactory.create(
            sessionRepository.findById(sessionId),
            sessionImageRepository.findAllBySessionId(sessionId)
        );
        Payments payments = paymentsFactory.create(session, paymentEntityUserMap);
        Payment newPayment = payment(newPaymentId);

        if (payments.canEnroll(session, newPayment)) {
            paymentRepository.save(newPayment.toPaymentEntity());
            return true;
        }

        return false;
    }
}
