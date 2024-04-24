package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.engine.Session;
import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.enrollment.SessionEnrollment;
import nextstep.courses.domain.session.enrollment.count.MaxRegistrationCount;
import nextstep.courses.domain.session.enrollment.state.SessionState;
import nextstep.courses.domain.session.feetype.FeeType;
import nextstep.courses.domain.session.name.SessionName;
import nextstep.courses.domain.session.period.Period;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.factory.EnrollmentCountFactory;
import nextstep.payments.domain.Money;

public class PaidSession extends Session {

    private final MaxRegistrationCount maxRegistrationCount;

    public PaidSession(SessionEntity sessionEntity) {
        this(sessionEntity.getId(),
            new SessionName(sessionEntity.getSessionName()),
            new SessionEnrollment(
                EnrollmentCountFactory.get(FeeType.valueOf(sessionEntity.getFeeType()),
                    sessionEntity.getRegistrationCount(),
                    sessionEntity.getMaxRegistrationCount()),
                new SessionState(
                    SessionState.valueOfRecruitmentState(sessionEntity.getRecruitmentState())),
                new Money(sessionEntity.getTuitionFee())),
            null,
            new Period(sessionEntity.getStartDate(), sessionEntity.getEndDate()),
            new MaxRegistrationCount(sessionEntity.getRegistrationCount()));
    }

    public PaidSession(SessionName SessionName, Enrollment enrollment, Image image, Period period,
        MaxRegistrationCount maxRegistrationCount) {
        this(null, SessionName, enrollment, image, period, maxRegistrationCount);
    }

    public PaidSession(Long id, SessionName SessionName,
        Enrollment enrollment, Image image, Period period,
        MaxRegistrationCount maxRegistrationCount) {
        super(id, SessionName, enrollment, image, period);
        this.maxRegistrationCount = maxRegistrationCount;
    }
}
