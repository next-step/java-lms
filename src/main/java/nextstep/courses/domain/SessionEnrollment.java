package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class SessionEnrollment {

    private SessionType type;

    private Fee fee;

    private Students maxStudents;

    public SessionEnrollment() {
    }

    public SessionEnrollment(SessionType type, Long fee, Integer maxStudents) {
        this.type = type;
        this.fee = new Fee(fee, type);
        this.maxStudents = new Students(maxStudents, type);
    }

    public void enroll(Long userId, Long sessionId) {
        if (type == SessionType.PAID) {
            maxStudents.enroll();
            fee.enroll(userId, sessionId);
        }
    }
}
