package nextstep.courses.fixture.builder;

import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.PaidSession;
import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.enrollment.count.MaxRegistrationCount;
import nextstep.courses.domain.session.name.SessionName;
import nextstep.courses.domain.session.period.Period;

public class PaidSessionBuilder {

    private SessionName SessionName;

    private Enrollment enrollment;

    private Image image;

    private Period period;

    private MaxRegistrationCount maxRegistrationCount;

    private PaidSessionBuilder() {}

    public static PaidSessionBuilder anFreeSession() {
        return new PaidSessionBuilder();
    }

    public PaidSessionBuilder withName(String name) {
        this.SessionName = new SessionName(name);
        return this;
    }

    public PaidSessionBuilder withName(Enrollment enrollment) {
        this.enrollment = enrollment;
        return this;
    }

    public PaidSessionBuilder withMaxRegistrationCount(int maxRegistrationCount) {
        this.maxRegistrationCount = new MaxRegistrationCount(maxRegistrationCount);
        return this;
    }

    public PaidSessionBuilder withImage(Image image) {
        this.image = image;
        return this;
    }

    public PaidSessionBuilder withValidityPeriod(Period period) {
        this.period = period;
        return this;
    }

    public PaidSession build() {
        return new PaidSession(SessionName, enrollment, image, period, maxRegistrationCount);
    }
}
