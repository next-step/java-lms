package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class SessionService {

    public void enroll(NsUser student, Integer sessionFee) {
        Session session = null; //Todo Session DB코드 추가필요.

        if (session.isEnrollmentPossible(sessionFee)) {
            session.enroll(student);
            //todo Payment 설정 필요
            Payment payment = new Payment("paymentId", session.getSessionId(), student.getId(), sessionFee);
            //todo student, payment DB저장 필요
        }

    }
}
