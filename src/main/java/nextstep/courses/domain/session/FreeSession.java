package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.engine.Session;
import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.enrollment.SessionEnrollment;
import nextstep.courses.domain.session.enrollment.state.SessionState;
import nextstep.courses.domain.session.feetype.FeeType;
import nextstep.courses.domain.session.name.SessionName;
import nextstep.courses.domain.session.period.Period;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.factory.EnrollmentCountFactory;
import nextstep.payments.domain.Money;

public class FreeSession extends Session {

    public FreeSession(SessionEntity sessionEntity) {
        this(sessionEntity.getId(),
            new SessionName(sessionEntity.getSessionName()),
            new SessionEnrollment(
                EnrollmentCountFactory.get(FeeType.valueOf(sessionEntity.getFeeType()),
                    sessionEntity.getRegistrationCount(),
                    sessionEntity.getMaxRegistrationCount()),
                new SessionState(
                    SessionState.valueOfRecruitmentState(sessionEntity.getRecruitmentState())),
                new Money(sessionEntity.getTuitionFee()),
                FeeType.valueOf(sessionEntity.getFeeType())),
            null,
            new Period(sessionEntity.getStartDate(), sessionEntity.getEndDate()));
    }

    public FreeSession(Session session, Enrollment enrollment) {
        this(session.getId(), new SessionName(session.getSessionName()), enrollment,
            session.getImage(), session.getPeriod());
    }

    public FreeSession(SessionName SessionName, Enrollment enrollment, Image image, Period period) {
        this(null, SessionName, enrollment, image, period);
    }

    public FreeSession(Long id, SessionName SessionName, Enrollment enrollment, Image image, Period period) {
        super(id, SessionName, enrollment, image, period);
    }
}
