package nextstep.courses.factory;

import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.PaidSession;
import nextstep.courses.domain.session.engine.Session;
import nextstep.courses.domain.session.enrollment.SessionEnrollment;
import nextstep.courses.domain.session.enrollment.count.MaxRegistrationCount;
import nextstep.courses.domain.session.enrollment.state.SessionState;
import nextstep.courses.domain.session.feetype.FeeType;
import nextstep.courses.domain.session.name.SessionName;
import nextstep.courses.domain.session.period.Period;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.error.exception.NotExistSessionType;
import nextstep.payments.domain.Money;

public class SessionFactory {

    private SessionFactory() {
    }

    public static Session get(SessionEntity sessionEntity) {
        if (FeeType.PAID == FeeType.valueOf(sessionEntity.getFeeType())) {
            return new PaidSession(sessionEntity.getId(),
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
                new Period(sessionEntity.getStartDate(), sessionEntity.getEndDate()),
                new MaxRegistrationCount(sessionEntity.getRegistrationCount()));
        }

        if (FeeType.FREE == FeeType.valueOf(sessionEntity.getFeeType())) {
            return new FreeSession(sessionEntity.getId(),
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

        throw new NotExistSessionType();
    }
}
