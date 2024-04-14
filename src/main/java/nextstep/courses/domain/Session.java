package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class Session {
    private SessionTime sessionTime;

    private SessionType sessionType;

    private SessionStatus sessionStatus;
    private SessionStudentCount sessionStudentCount;
    private Image image;
    private Long fee;

    public Session(SessionTime sessionTime, SessionType sessionType, SessionStatus sessionStatus, Image image, SessionStudentCount sessionStudentCount, Long fee) {
        this.sessionStatus = sessionStatus;
        this.sessionTime = sessionTime;
        this.sessionType = sessionType;
        this.image = image;
        this.sessionStudentCount = sessionStudentCount;
        this.fee = fee;
    }

    public void enroll(Payment payment) {
        validateRecruiting();
        if (SessionType.isPaid(this.sessionType)) {
            validateMaxStudentsNumber();
            validatePayment(payment);
        }
        this.sessionStudentCount.addStudent();
    }

    private void validateMaxStudentsNumber() {
        this.sessionStudentCount.validateStudentCount();
    }

    private void validatePayment(Payment payment) {
        if (!payment.match(this.fee)) {
            throw new IllegalArgumentException("금액이 맞지 않습니다.");
        }
    }

    private void validateRecruiting() {
        if (!SessionStatus.isRecruiting(sessionStatus)) {
            throw new IllegalArgumentException("현재 모집 중인 강의가 아닙니다.");
        }
    }
}
