package nextstep.courses.service;

import nextstep.courses.domain.course.session.*;
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

    public void apply(NsUser loginUser, Long sessionId, Payment payment, LocalDateTime date) {
        Session session = getSession(sessionId);
        Enrollment enrollment = session.enrollment();
        Apply apply = enrollment.apply(loginUser.getId(), payment, date);
        applyRepository.save(apply);
    }

    public void approve(NsUser loginUser, Long applicantId, Long sessionId, LocalDateTime date) {
        Session session = getSession(sessionId);
        Apply savedApply = getApply(sessionId, applicantId);
        Approve approve = session.approve();
        Apply apply = approve.approve(loginUser, savedApply, date);
        applyRepository.update(apply);
    }

    public void cancel(NsUser loginUser, Long applicantId, Long sessionId, LocalDateTime date) {
        Session session = getSession(sessionId);
        Apply savedApply = getApply(sessionId, applicantId);
        Cancel cancel = session.cancel();
        Apply apply = cancel.cancel(loginUser, savedApply, date);
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
