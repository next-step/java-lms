package nextstep.courses.domain.session.impl;

import nextstep.courses.entity.BaseEntity;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.RegistrationCount;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.ValidityPeriod;
import nextstep.courses.entity.SessionEntity;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public class FreeSession extends BaseEntity implements Session {

    private Long id;

    private final SessionName sessionName;

    private final RegistrationCount registrationCount;

    private final Money tuitionFee;

    private final Image image;

    private final SessionStatus sessionStatus;

    private final ValidityPeriod validityPeriod;

    public FreeSession(SessionName sessionName, Image image, SessionStatus sessionStatus,
        ValidityPeriod validityPeriod) {
        this(null,
            sessionName,
            new RegistrationCount(0),
            new Money(0),
            image,
            sessionStatus,
            validityPeriod);
    }

    public FreeSession(SessionEntity sessionEntity, Image image) {
        this(sessionEntity.getId(),
            new SessionName(sessionEntity.getSessionName()),
            new RegistrationCount(sessionEntity.getRegistrationCount()),
            new Money(sessionEntity.getTuitionFee()),
            image,
            SessionStatus.valueOf(sessionEntity.getSessionStatus()),
            new ValidityPeriod(sessionEntity.getStartDate(), sessionEntity.getUpdatedAt()));
    }

    public FreeSession(Long id, SessionName sessionName, RegistrationCount registrationCount,
        Money tuitionFee,
        Image image, SessionStatus sessionStatus, ValidityPeriod validityPeriod) {
        this.id = id;
        this.sessionName = sessionName;
        this.registrationCount = registrationCount;
        this.tuitionFee = tuitionFee;
        this.image = image;
        this.sessionStatus = sessionStatus;
        this.validityPeriod = validityPeriod;
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getSessionName() {
        return sessionName.getValue();
    }

    @Override
    public int getRegistrationCount() {
        return registrationCount.getValue();
    }

    @Override
    public int getTuitionFee() {
        return tuitionFee.getValue();
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public String getSessionStatus() {
        return sessionStatus.getValue();
    }

    @Override
    public ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }
}
