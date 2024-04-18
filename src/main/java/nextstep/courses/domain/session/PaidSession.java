package nextstep.courses.domain.session;

import nextstep.courses.constant.SessionStatus;
import nextstep.courses.constant.SessionType;
import nextstep.courses.domain.SessionImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class PaidSession extends Session {
    private final int capacity;
    private final Long fee;

    public PaidSession(
            Long courseId,
            Long sessionId,
            SessionImage sessionImage,
            LocalDateTime startTime,
            LocalDateTime endTime,
            SessionStatus sessionStatus,
            int capacity,
            Long fee
    ) {
        super(courseId, sessionId, sessionImage, startTime, endTime, sessionStatus, SessionType.PAID);
        this.capacity = capacity;
        this.fee = fee;
    }

    @Override
    public void enroll(NsUser nsUser) {
        throw new UnsupportedOperationException("유료 강의는 결제를 통해 수강 신청이 가능합니다.");
    }

    public void enroll(NsUser nsUser, Payment payment) {
        validatePaidSessionEnroll(nsUser, payment);
        super.enrollStudent(nsUser);
    }

    private void validatePaidSessionEnroll(NsUser nsUser, Payment payment) {
        validateSession(nsUser);

        if (!payment.isPaid(fee)) {
            throw new IllegalArgumentException("결제 금액이 일치하지 않습니다.");
        }

        if (super.getEnrolledStudentCount() >= capacity) {
            throw new IllegalArgumentException("수강 인원이 초과되었습니다.");
        }
    }
}
