package nextstep.payments.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.factory.SessionFactory;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentEntityUserMap;
import nextstep.payments.domain.PaymentRepository;
import nextstep.payments.domain.Payments;
import nextstep.payments.entity.PaymentEntity;
import nextstep.payments.factory.PaymentEntityFactory;
import nextstep.payments.factory.PaymentFactory;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class PaymentService {

    private final SessionRepository sessionRepository;
    private final SessionImageRepository sessionImageRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final SessionFactory sessionFactory;
    private final PaymentFactory paymentFactory;

    public PaymentService(
        SessionRepository sessionRepository,
        SessionImageRepository sessionImageRepository,
        PaymentRepository paymentRepository,
        UserRepository userRepository,
        SessionFactory sessionFactory,
        PaymentFactory paymentFactory
    ) {
        this.sessionRepository = sessionRepository;
        this.sessionImageRepository = sessionImageRepository;
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.sessionFactory = sessionFactory;
        this.paymentFactory = paymentFactory;
    }

    public Payment payment(String id) {
        // PG사 API를 통해 id에 해당하는 결제 정보를 반환
        return new Payment();
    }

    public boolean save(String newPaymentId, long sessionId) throws IOException {
        PaymentEntityUserMap paymentEntityUserMap = new PaymentEntityUserMap();
        paymentRepository.findBySession(sessionId).forEach(paymentEntity -> {
            NsUser user = userRepository.findByUserId(paymentEntity.getUserId().toString());
            paymentEntityUserMap.add(paymentEntity, user);
        });

        Session session = sessionFactory.createSession(
            sessionRepository.findById(sessionId),
            sessionImageRepository.findAllBySessionId(sessionId)
        );
        Payments payments = paymentFactory.createPayments(session, paymentEntityUserMap);
        Payment newPayment = payment(newPaymentId);

        if (payments.canEnroll(session, newPayment)) {
            paymentRepository.save(paymentFactory.createPaymentEntity(newPayment));
            return true;
        }

        return false;
    }

    @Transactional
    public boolean approve(long paymentId, String approverId) throws IOException {
        NsUser approver = userRepository.findByUserId(approverId);
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId);
        NsUser applicant = userRepository.findByUserId(paymentEntity.getUserId().toString());

        if (approver.canApprove(applicant)) {
            Long sessionId = paymentEntity.getSessionId();
            Session session = sessionFactory.createSession(
                sessionRepository.findById(sessionId),
                sessionImageRepository.findAllBySessionId(sessionId)
            );
            paymentFactory.createPayment(paymentEntity, session, applicant).approve();
            return true;
        }

        return false;
    }

    @Transactional
    public boolean cancel(long paymentId, String approverId) throws IOException {
        NsUser approver = userRepository.findByUserId(approverId);
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId);
        NsUser applicant = userRepository.findByUserId(paymentEntity.getUserId().toString());

        if (approver.canCancel(applicant)) {
            Long sessionId = paymentEntity.getSessionId();
            Session session = sessionFactory.createSession(
                sessionRepository.findById(sessionId),
                sessionImageRepository.findAllBySessionId(sessionId)
            );
            paymentFactory.createPayment(paymentEntity, session, applicant).cancel();
            return true;
        }

        return false;
    }

}
