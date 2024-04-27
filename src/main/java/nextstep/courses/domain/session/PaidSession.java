package nextstep.courses.domain.session;

import java.util.List;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.engine.Session;
import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.enrollment.count.MaxRegistrationCount;
import nextstep.courses.domain.session.name.SessionName;
import nextstep.courses.domain.session.period.Period;

public class PaidSession extends Session {

    private final MaxRegistrationCount maxRegistrationCount;

    public PaidSession(SessionName SessionName, Enrollment enrollment, Image image, List<Image> imageTmp, Period period,
        MaxRegistrationCount maxRegistrationCount) {
        this(null, SessionName, enrollment, image, imageTmp, period, maxRegistrationCount);
    }

    public PaidSession(Long id, SessionName SessionName,
        Enrollment enrollment, Image image, List<Image> imageTmp, Period period,
        MaxRegistrationCount maxRegistrationCount) {
        super(id, SessionName, enrollment, image, imageTmp, period);
        this.maxRegistrationCount = maxRegistrationCount;
    }
}
