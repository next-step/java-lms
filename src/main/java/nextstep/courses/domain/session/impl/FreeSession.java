package nextstep.courses.domain.session.impl;

import java.time.LocalDateTime;
import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.RegistrationCount;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public class FreeSession extends BaseEntity implements Session {

    private final SessionName sessionName;

    private final RegistrationCount registrationCount;

    private final Money tuitionFee;

    private final Image image;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    private final SessionStatus sessionStatus;

    public FreeSession(SessionName sessionName, LocalDateTime startDate, LocalDateTime endDate, Image image,
        SessionStatus sessionStatus) {
        this(sessionName, new RegistrationCount(0), new Money(0), image, startDate, endDate,
            sessionStatus);
    }

    public FreeSession(SessionName sessionName, RegistrationCount registrationCount,
        Money tuitionFee,
        Image image, LocalDateTime startDate, LocalDateTime endDate, SessionStatus sessionStatus) {
        this.sessionName = sessionName;
        this.registrationCount = registrationCount;
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
        return isRecruitmentOpen(sessionStatus);
    }

    @Override
    public boolean isPaymentAmountSameTuitionFee(Payment payment) {
        return payment.isSamePaymentAmount(tuitionFee);
    }
}
