package nextstep.courses.fixture.builder;

import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.enrollment.SessionEnrollment;
import nextstep.courses.domain.session.enrollment.count.engine.EnrollmentCount;
import nextstep.courses.domain.session.enrollment.state.SessionState;
import nextstep.courses.domain.session.feetype.FeeType;
import nextstep.payments.domain.Money;

public class EnrollmentBuilder {

    private Long id;

    private EnrollmentCount enrollmentCount;

    private SessionState sessionState;

    private Money tuitionFee;

    private FeeType feeType;

    public static EnrollmentBuilder anEnrollment() {
        return new EnrollmentBuilder();
    }

    public EnrollmentBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public EnrollmentBuilder withEnrollmentCount(EnrollmentCount enrollmentCount) {
        this.enrollmentCount = enrollmentCount;
        return this;
    }

    public EnrollmentBuilder withSessionState(SessionState sessionState) {
        this.sessionState = sessionState;
        return this;
    }

    public EnrollmentBuilder withTuitionFee(Money tuitionFee) {
        this.tuitionFee = tuitionFee;
        return this;
    }
    public EnrollmentBuilder withFeeType(FeeType feeType) {
        this.feeType = feeType;
        return this;
    }

    public Enrollment build() {
        return new SessionEnrollment(id, enrollmentCount, sessionState, tuitionFee, feeType);
    }
}
