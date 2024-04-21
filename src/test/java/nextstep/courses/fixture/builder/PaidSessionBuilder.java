package nextstep.courses.fixture.builder;

import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.MaxRegistrationCount;
import nextstep.courses.domain.session.RegistrationCount;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.ValidityPeriod;
import nextstep.courses.domain.session.impl.FreeSession;
import nextstep.payments.domain.Money;

public class PaidSessionBuilder {

    private SessionName name;

    private RegistrationCount registrationCount;

    private MaxRegistrationCount maxRegistrationCount;

    private Money tuitionFee;

    private Image image;

    private SessionStatus sessionStatus;

    private ValidityPeriod validityPeriod;

    private PaidSessionBuilder() {}

    public static PaidSessionBuilder anFreeSession() {
        return new PaidSessionBuilder();
    }

    public PaidSessionBuilder withName(String name) {
        this.name = new SessionName(name);
        return this;
    }

    public PaidSessionBuilder withMaxRegistrationCount(int maxRegistrationCount) {
        this.maxRegistrationCount = new MaxRegistrationCount(new RegistrationCount(maxRegistrationCount));
        return this;
    }

    public PaidSessionBuilder withTuitionFee(int tuitionFee) {
        this.tuitionFee = new Money(tuitionFee);
        return this;
    }

    public PaidSessionBuilder withImage(Image image) {
        this.image = image;
        return this;
    }

    public PaidSessionBuilder withSessionStatus(SessionStatus status) {
        this.sessionStatus = status;
        return this;
    }

    public PaidSessionBuilder withValidityPeriod(ValidityPeriod validityPeriod) {
        this.validityPeriod = validityPeriod;
        return this;
    }

    public FreeSession build() {
        return new FreeSession(name, image, sessionStatus, validityPeriod);
    }
}
