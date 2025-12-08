package nextstep.courses.domain.session;

import nextstep.courses.domain.session.constant.SessionStatus;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public interface SessionCoreFacade {

    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
    int getMaxCapacity();
    Long getTuition();
    String getSessionType();
    SessionPolicy getSessionPolicy();
    SessionRange getSessionRange();
    SessionStatus getSessionStatus();

    void validatePaymentAmount(Payment payment);
    void validateNotFull(Enrollments enrollments);
    void validateSessionStatus();
    void validateRecruitmentStatus();
}
