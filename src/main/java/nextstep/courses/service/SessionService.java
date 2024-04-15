package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class SessionService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Transactional
    public void enrollSession (NsUser nsUser,
                              Long sessionId,
                              Payment payment) throws Exception {
        Session session = sessionRepository.findById(sessionId).orElseThrow(NotFoundException::new);
        session.enroll(nsUser, payment);
    }
}
