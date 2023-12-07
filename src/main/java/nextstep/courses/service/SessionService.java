package nextstep.courses.service;

import nextstep.courses.domain.course.session.Session;
import nextstep.courses.domain.course.session.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sessionService")
public class SessionService {
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    public void applySession(NsUser loginUser, long sessionId, Payment payment) {
        Session session = getSession(sessionId);
        session.apply(loginUser, payment);
    }

    private Session getSession(long sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(NotFoundException::new);
    }
}
