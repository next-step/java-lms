package nextstep.courses.domain.session.impl;

import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.RegistrationCount;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.ValidityPeriod;
import nextstep.courses.entity.SessionEntity;
import nextstep.payments.domain.Money;

public class FreeSession extends AbstractSession {

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
        super(id, sessionName, registrationCount, tuitionFee, image, sessionStatus, validityPeriod);
    }
}
