package nextstep.courses.service;

import nextstep.courses.domain.course.session.Apply;
import nextstep.courses.domain.course.session.Session;
import nextstep.courses.domain.course.session.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service("sessionService")
public class SessionService {
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    public void create(Long courseId, Session session, LocalDateTime date) {
        sessionRepository.save(courseId, session);
    }

    public void applySession(NsUser loginUser, Long sessionId, Payment payment, LocalDateTime date) {
        Session session = getSession(sessionId);
        Apply apply = session.apply(loginUser, payment, date);
        sessionRepository.saveApply(apply);
    }

    public void changeOnReady(Long sessionId, LocalDate date) {
        Session session = getSession(sessionId);
        session.changeOnReady(date);
        sessionRepository.update(sessionId, session);
    }

    public void changeOnRecruit(Long sessionId, LocalDate date) {
        Session session = getSession(sessionId);
        session.changeOnRecruit(date);
        sessionRepository.update(sessionId, session);
    }

    public void changeOnEnd(Long sessionId, LocalDate date) {
        Session session = getSession(sessionId);
        session.changeOnEnd(date);
        sessionRepository.update(sessionId, session);
    }

    private Session getSession(Long sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(NotFoundException::new);
    }
}
