package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.infrastructure.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("sessionService")
public class SessionService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Transactional
    public void register(long sessionId, NsUser user, Payment payment) {
        Session session = sessionRepository.findById(sessionId);
        session.register(payment);
    }
}
