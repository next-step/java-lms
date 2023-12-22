package nextstep.courses.service;

import nextstep.courses.domain.course.session.Session;
import nextstep.courses.domain.course.session.SessionRepository;
import nextstep.courses.domain.course.session.apply.Apply;
import nextstep.courses.domain.course.session.apply.ApplyRepository;
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

    @Resource(name = "applyRepository")
    private ApplyRepository applyRepository;

    public void create(Long courseId, Session session) {
        sessionRepository.save(courseId, session);
    }

    public void applySession(NsUser loginUser, Long sessionId, Payment payment, LocalDateTime date) {
        Session session = getSession(sessionId);
        Apply apply = session.apply(loginUser, payment, date);
        applyRepository.save(apply);
    }

    public void approve(NsUser loginUser, Long applicantId, Long sessionId, LocalDateTime date) {
        Session session = getSession(sessionId);
        Apply savedApply = getApply(sessionId, applicantId);
        Apply apply = session.approve(loginUser, savedApply, date);
        applyRepository.update(apply);
    }

    public void cancel(NsUser loginUser, Long applicantId, Long sessionId, LocalDateTime date) {
        Session session = getSession(sessionId);
        Apply savedApply = getApply(sessionId, applicantId);
        Apply apply = session.cancel(loginUser, savedApply, date);
        applyRepository.update(apply);
    }

    public void changeOnReady(Long sessionId, LocalDate date) {
        Session session = getSession(sessionId);
        session.changeOnReady(date);
        sessionRepository.update(sessionId, session);
    }

    public void changeOnGoing(Long sessionId, LocalDate date) {
        Session session = getSession(sessionId);
        session.changeOnGoing(date);
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

    private Apply getApply(Long sessionId, Long nsUserId) {
        return applyRepository.findApplyByNsUserIdAndSessionId(sessionId, nsUserId).orElseThrow(NotFoundException::new);
    }
}
