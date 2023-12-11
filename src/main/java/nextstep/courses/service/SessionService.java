package nextstep.courses.service;

import nextstep.courses.domain.course.session.Session;
import nextstep.courses.domain.course.session.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

@Service("sessionService")
public class SessionService {
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    public void create(Long courseId, Session session) {
        sessionRepository.save(courseId, session);
    }

    public void applySession(NsUser loginUser, long sessionId, Payment payment) {
        Session session = getSession(sessionId);
        session.apply(loginUser, payment);
        sessionRepository.saveApply(loginUser, session);
    }

    public void changeOnReady(long sessionId, LocalDate date) {
        Session session = getSession(sessionId);
        session.changeOnReady(date);
        sessionRepository.update(sessionId, session);
    }

    public void changeOnRecruit(long sessionId, LocalDate date) {
        Session session = getSession(sessionId);
        session.changeOnRecruit(date);
        sessionRepository.update(sessionId, session);
    }

    public void changeOnEnd(long sessionId, LocalDate date) {
        Session session = getSession(sessionId);
        session.changeOnEnd(date);
        sessionRepository.update(sessionId, session);
    }

    private Session getSession(long sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(NotFoundException::new);
    }
}
