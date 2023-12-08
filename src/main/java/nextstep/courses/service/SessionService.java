package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void enroll(Long sessionId, NsUser student, Integer sessionFee) {
        Session session = sessionRepository.findById(sessionId);

        if (session.isEnrollmentPossible(sessionFee)) {
            session.enroll(student);
            //todo Payment 설정 필요
            Payment payment = new Payment("paymentId", session.getSessionId(), student.getId(), sessionFee);
            //todo student, payment DB저장 필요
        }

    }
}
