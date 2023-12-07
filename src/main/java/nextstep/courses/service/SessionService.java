package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("sessionService")
public class SessionService {
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "sessionEnrollRepository")
    private SessionEnrollRepository sessionEnrollRepository;

    @Transactional
    public void makeFreeSession(Long courseId, SessionVO sessionVO) {
        Session freeSession = Session.freeSession(
                sessionVO.getId(),
                courseId,
                sessionVO.getStartDate(),
                sessionVO.getEndDate(),
                sessionVO.getImage(),
                sessionVO.getAppliedNumber(),
                sessionVO.getSessionStatus());
        sessionRepository.save(freeSession);
    }

    public void makePaidSession(Long courseId, SessionVO sessionVO) {
        Session paidSession = Session.paidSession(
                sessionVO.getId(),
                courseId,
                sessionVO.getStartDate(),
                sessionVO.getEndDate(),
                sessionVO.getImage(),
                sessionVO.getMaxStudentNumber(),
                sessionVO.getAppliedNumber(),
                sessionVO.getSessionFee(),
                sessionVO.getSessionStatus());
        sessionRepository.save(paidSession);
    }

    public void enrollStudent(Session session, Student student, Payment payment) {
        session.enroll(payment);
        SessionEnroll sessionEnroll = new SessionEnroll(session.getId(), student.getId(), payment.getId());
        sessionEnrollRepository.save(sessionEnroll);
    }
}
