package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

public interface Session {

    default boolean isRecruitmentOpen(SessionStatus sessionStatus) {
        return SessionStatus.RECRUITING == sessionStatus;
    }

    void addRegistrationCount();

    boolean isRegistrationAvailable();

    boolean isPaymentAmountSameTuitionFee(Payment payment);
}
