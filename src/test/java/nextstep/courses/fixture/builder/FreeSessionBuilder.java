package nextstep.courses.fixture.builder;

import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.RegistrationCount;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.impl.FreeSession;
import nextstep.payments.domain.Money;

public class FreeSessionBuilder {
    private SessionName name;

    private RegistrationCount registrationCount;

    private Money tuitionFee;

    private Image image;

    private SessionStatus sessionStatus;

    private Period period;

    private FreeSessionBuilder() {}

    public static FreeSessionBuilder anFreeSession() {
        return new FreeSessionBuilder();
    }

    public FreeSessionBuilder withName(String name) {
        this.name = new SessionName(name);
        return this;
    }

    public FreeSessionBuilder withImage(Image image) {
        this.image = image;
        return this;
    }

    public FreeSessionBuilder withSessionStatus(SessionStatus status) {
        this.sessionStatus = status;
        return this;
    }

    public FreeSessionBuilder withValidityPeriod(Period period) {
        this.period = period;
        return this;
    }

    public FreeSession build() {
        return new FreeSession(name, image, sessionStatus, period);
    }
}
