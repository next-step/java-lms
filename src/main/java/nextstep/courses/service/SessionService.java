package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("sessionService")
public class SessionService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "paymentService")
    private PaymentService paymentService;

    @Transactional
    public void enroll(NsUser loginUser, long sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));

        session.enroll(loginUser, paymentService.payment(loginUser.getUserId()));
    }
}
