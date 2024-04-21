package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import nextstep.courses.domain.cover.Image;
import nextstep.payments.domain.Payment;

public interface Session {

    default boolean isRecruitmentOpen(SessionStatus sessionStatus) {
        return SessionStatus.RECRUITING == sessionStatus;
    }

    default int getMaxRegistrationCount(){
        return new MaxRegistrationCount(new RegistrationCount(Integer.MAX_VALUE)).getRegistrationCount();
    };

    void addRegistrationCount();

    boolean isRegistrationAvailable();

    boolean isPaymentAmountSameTuitionFee(Payment payment);

    Long getId();

    String getSessionName();

    int getRegistrationCount();

    int getTuitionFee();

    Image getImage();

    String getSessionStatus();

    ValidityPeriod getValidityPeriod();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
