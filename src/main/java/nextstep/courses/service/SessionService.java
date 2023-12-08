package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.courses.exception.SessionException;
import nextstep.payments.domain.Payment;
import nextstep.tutor.domain.NsTutor;
import nextstep.users.domain.NsUser;
import nextstep.tutor.domain.TutorRepository;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("sessionService")
public class SessionService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "sessionUsersRepository")
    private SessionUsersRepository sessionUsersRepository;

    @Resource(name = "tutorRepository")
    private TutorRepository tutorRepository;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Transactional
    public void register(long sessionId, NsUser user, Payment payment) {
        SessionUsers sessionUsers = sessionUsersRepository.findBy(sessionId);
        Session session = sessionRepository.findBy(sessionId)
                .orElseThrow(() -> new SessionException("강의 정보를 찾을 수 없습니다."));
        session.register(user, payment);
        sessionUsersRepository.addUserFor(sessionId, user.getId());
    }

    @Transactional
    public void approve(long sessionId, NsTutor tutor, NsUser user) {
        Session session = sessionRepository.findBy(sessionId)
                .orElseThrow(() -> new SessionException("강의 정보를 찾을 수 없습니다."));
        SessionUserStatus approve = tutor.approve(session, user);
        sessionUsersRepository.updateSessionUserStatus(sessionId, user.getId(), approve);
    }

    @Transactional
    public void cancel(long sessionId, NsTutor tutor, NsUser user) {
        Session session = sessionRepository.findBy(sessionId)
                .orElseThrow(() -> new SessionException("강의 정보를 찾을 수 없습니다."));
        SessionUserStatus cancel = tutor.cancel(session);
        sessionUsersRepository.updateSessionUserStatus(sessionId, user.getId(), cancel);
    }

}
