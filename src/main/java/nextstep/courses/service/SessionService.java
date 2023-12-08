package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.courses.exception.SessionException;
import nextstep.payments.domain.Payment;
import nextstep.tutor.domain.NsTutor;
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
    public void register(long sessionId, SelectionStatus selectionStatus, NsUser user, Payment payment) {
        SessionUsers sessionUsers = sessionUsersRepository.findBy(sessionId);
        Session session = sessionRepository.findBy(sessionId)
                .orElseThrow(() -> new SessionException("강의 정보를 찾을 수 없습니다."));
        SessionUser sessionUser = session.register(selectionStatus, user, payment);
        sessionUsersRepository.addUserFor(sessionUser);
    }

    @Transactional
    public void approve(long sessionId, NsTutor tutor, NsUser user) {
        Session session = sessionRepository.findBy(sessionId)
                .orElseThrow(() -> new SessionException("강의 정보를 찾을 수 없습니다."));

        SessionUser sessionUser = session.approve(tutor, user);
        sessionUsersRepository.save(sessionUser);
    }

    @Transactional
    public void cancel(long sessionId, NsTutor tutor, NsUser user) {
        Session session = sessionRepository.findBy(sessionId)
                .orElseThrow(() -> new SessionException("강의 정보를 찾을 수 없습니다."));
        SessionUser sessionUser = session.cancel(tutor, user);
        sessionUsersRepository.save(sessionUser);
    }

}
