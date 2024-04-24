package nextstep.courses.factory;

import nextstep.courses.domain.session.enrollment.count.FreeEnrollmentCount;
import nextstep.courses.domain.session.enrollment.count.MaxRegistrationCount;
import nextstep.courses.domain.session.enrollment.count.PaidEnrollmentCount;
import nextstep.courses.domain.session.enrollment.count.RegistrationCount;
import nextstep.courses.domain.session.enrollment.count.engine.EnrollmentCount;
import nextstep.courses.domain.session.feetype.FeeType;
import nextstep.courses.error.exception.NotExistEnrollmentCountType;

public class EnrollmentCountFactory {

    private EnrollmentCountFactory() {
    }

    public static EnrollmentCount get(FeeType feeType, int registrationCount, int maxRegistrationCount){
        if (FeeType.FREE == feeType){
            return new FreeEnrollmentCount(new RegistrationCount(registrationCount));
        }

        if (FeeType.PAID == feeType){
            return new PaidEnrollmentCount(new RegistrationCount(registrationCount), new MaxRegistrationCount(maxRegistrationCount));
        }

        throw new NotExistEnrollmentCountType();
    }
}
