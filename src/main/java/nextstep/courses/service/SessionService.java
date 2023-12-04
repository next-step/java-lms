package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionEnroll;
import nextstep.courses.domain.SessionVO;
import nextstep.courses.domain.Student;
import nextstep.payments.domain.Payment;

public class SessionService {
    public Session makeSession(Long courseId, SessionVO sessionVO) {
        return new Session(
                sessionVO.getId(),
                courseId,
                sessionVO.getStartDate(),
                sessionVO.getEndDate(),
                sessionVO.getImageInfo(),
                sessionVO.getPaidType(),
                sessionVO.getTargetNumber(),
                sessionVO.getAppliedNumber(),
                sessionVO.getSessionFee(),
                sessionVO.getSessionStatus());
    }

    public SessionEnroll enrollStudent(Session session, Student student, Payment payment) {
        return new SessionEnroll(session, student, payment);
    }
}
