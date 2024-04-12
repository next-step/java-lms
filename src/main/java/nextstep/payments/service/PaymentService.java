package nextstep.payments.service;

import javax.annotation.Resource;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Resource(name = "paymentRepository")
    private PaymentRepository paymentRepository;

    public Payment payment(Long sessionId, Long userId) {
       return paymentRepository.findBySessionAndUser(sessionId, userId);
    }
}
