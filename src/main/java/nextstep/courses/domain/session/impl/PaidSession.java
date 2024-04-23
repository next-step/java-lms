package nextstep.courses.domain.session.impl;

import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.MaxRegistrationCount;
import nextstep.courses.domain.session.RegistrationCount;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.ValidityPeriod;
import nextstep.courses.entity.SessionEntity;
import nextstep.payments.domain.Money;

public class PaidSession extends AbstractSession {

    private final MaxRegistrationCount maxRegistrationCount;

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
        super(id, sessionName, registrationCount, tuitionFee, image, sessionStatus, validityPeriod);
        this.maxRegistrationCount = maxRegistrationCount;
    }

    @Override
    public boolean isRegistrationAvailable() {
        return isRecruitmentOpen(sessionStatus)
            && maxRegistrationCount.isMaxRegistrationCountOver(registrationCount);
    }
}
