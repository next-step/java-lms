package nextstep.courses.domain.session.impl;

import java.time.LocalDateTime;
import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.MaxRegistrationCount;
import nextstep.courses.domain.session.RegistrationCount;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public class PaidSession extends BaseEntity implements Session {

    private final SessionName sessionName;

    private final RegistrationCount registrationCount;

    private final MaxRegistrationCount maxRegistrationCount;

    private final Money tuitionFee;

    private final Image image;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    private SessionStatus sessionStatus;

    public PaidSession(SessionName sessionName, RegistrationCount registrationCount,
        MaxRegistrationCount maxRegistrationCount, Money tuitionFee, Image image,
        LocalDateTime startDate, LocalDateTime endDate, SessionStatus sessionStatus) {
        this.sessionName = sessionName;
        this.registrationCount = registrationCount;
        this.maxRegistrationCount = maxRegistrationCount;
        this.tuitionFee = tuitionFee;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionStatus = sessionStatus;
    }

    @Override
    public void addRegistrationCount() {
        registrationCount.addValue();
    }

    @Override
    public boolean isRegistrationAvailable() {
        return isRecruitmentOpen(sessionStatus)
            && maxRegistrationCount.isMaxRegistrationCountOver(this.registrationCount);
    }

    @Override
    public boolean isPaymentAmountSameTuitionFee(Payment payment){
        return payment.isSamePaymentAmount(tuitionFee);
    }
}
