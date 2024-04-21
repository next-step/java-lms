package nextstep.courses.domain.session.impl;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.MaxRegistrationCount;
import nextstep.courses.domain.session.RegistrationCount;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.ValidityPeriod;
import nextstep.courses.entity.SessionEntity;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public class PaidSession extends BaseEntity implements Session {

    private Long id;

    private final SessionName sessionName;

    private final RegistrationCount registrationCount;

    private final MaxRegistrationCount maxRegistrationCount;

    private final Money tuitionFee;

    private final Image image;

    private SessionStatus sessionStatus;

    private final ValidityPeriod validityPeriod;

    public PaidSession(SessionEntity sessionEntity, Image image) {
        this(sessionEntity.getId(),
            new SessionName(sessionEntity.getSessionName()),
            new RegistrationCount(sessionEntity.getRegistrationCount()),
            new MaxRegistrationCount(
                new RegistrationCount(sessionEntity.getMaxRegistrationCount())),
            new Money(sessionEntity.getTuitionFee()),
            image,
            SessionStatus.valueOf(sessionEntity.getSessionStatus()),
            new ValidityPeriod(sessionEntity.getStartDate(),
                sessionEntity.getEndDate()));
    }

    public PaidSession(SessionName sessionName,
        MaxRegistrationCount maxRegistrationCount, Money tuitionFee, Image image,
        SessionStatus sessionStatus, ValidityPeriod validityPeriod) {
        this(null, sessionName, new RegistrationCount(0), maxRegistrationCount, tuitionFee, image,
            sessionStatus, validityPeriod);
    }

    public PaidSession(Long id, SessionName sessionName, RegistrationCount registrationCount,
        MaxRegistrationCount maxRegistrationCount, Money tuitionFee, Image image,
        SessionStatus sessionStatus, ValidityPeriod validityPeriod) {
        this.id = id;
        this.sessionName = sessionName;
        this.registrationCount = registrationCount;
        this.maxRegistrationCount = maxRegistrationCount;
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
        return isRecruitmentOpen(sessionStatus)
            && maxRegistrationCount.isMaxRegistrationCountOver(registrationCount);
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
    public int getMaxRegistrationCount() {
        return maxRegistrationCount.getRegistrationCount();
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
