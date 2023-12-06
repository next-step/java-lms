package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionUsers;
import nextstep.courses.domain.SessionUsersRepository;
import nextstep.courses.exception.SessionException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("sessionService")
public class SessionService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "sessionUsersRepository")
    private SessionUsersRepository sessionUsersRepository;

    @Transactional
    public void register(long sessionId, NsUser user, Payment payment) {
        SessionUsers sessionUsers = sessionUsersRepository.findBy(sessionId);
        Session session = sessionRepository.findBy(sessionId)
                .orElseThrow(() -> new SessionException("강의 정보를 찾을 수 없습니다."));
        session.register(user, payment);
        sessionUsersRepository.addUserFor(sessionId, user.getId());
    }
}
