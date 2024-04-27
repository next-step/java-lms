package nextstep.courses.fixture.builder;

import java.util.List;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.name.SessionName;
import nextstep.courses.domain.session.period.Period;

public class FreeSessionBuilder {

    private SessionName SessionName;

    private Enrollment enrollment;

    private Image image;

    private List<Image> imageTmp;

    private Period period;

    public static FreeSessionBuilder anFreeSession() {
        return new FreeSessionBuilder();
    }

    public FreeSessionBuilder withName(String name) {
        this.SessionName = new SessionName(name);
        return this;
    }

    public FreeSessionBuilder withEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
        return this;
    }

    public FreeSessionBuilder withImage(Image image) {
        this.image = image;
        return this;
    }

    public FreeSessionBuilder withImageTmp(List<Image> imageTmp) {
        this.imageTmp = imageTmp;
        return this;
    }

    public FreeSessionBuilder withValidityPeriod(Period period) {
        this.period = period;
        return this;
    }

    public FreeSession build() {
        return new FreeSession(SessionName, enrollment, image, imageTmp, period);
    }
}
