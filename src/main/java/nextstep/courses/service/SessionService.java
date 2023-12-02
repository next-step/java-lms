package nextstep.courses.service;

import nextstep.courses.domain.Apply;
import nextstep.courses.domain.ApplyRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Service("sessionService")
public class SessionService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "applyRepository")
    private ApplyRepository applyRepository;

    @Transactional
    public void enrolment (Payment payment) {
        Session session = sessionRepository.findById(payment.getSessionId());
        NsUser nsUser = userRepository.findById(payment.getNsUserId());

        Apply apply = session.enrolment(nsUser, payment.getAmount());
        applyRepository.save(apply);
    }
}
