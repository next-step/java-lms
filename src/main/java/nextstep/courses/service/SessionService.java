package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionEnroll;
import nextstep.courses.domain.SessionVO;
import nextstep.courses.domain.Student;
import nextstep.payments.domain.Payment;

public class SessionService {

    public Session makeFreeSession(Long courseId, SessionVO sessionVO) {
        return Session.freeSession(
                sessionVO.getId(),
                courseId,
                sessionVO.getStartDate(),
                sessionVO.getEndDate(),
                sessionVO.getImage(),
                sessionVO.getAppliedNumber(),
                sessionVO.getSessionStatus());
    }

    public Session makePaidSession(Long courseId, SessionVO sessionVO) {
        return Session.paidSession(
                sessionVO.getId(),
                courseId,
                sessionVO.getStartDate(),
                sessionVO.getEndDate(),
                sessionVO.getImage(),
                sessionVO.getMaxStudentNumber(),
                sessionVO.getAppliedNumber(),
                sessionVO.getSessionFee(),
                sessionVO.getSessionStatus());
    }

    public SessionEnroll enrollStudent(Session session, Student student, Payment payment) {
        return session.enroll(student, payment);
    }
}
