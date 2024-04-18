package nextstep.courses.domain.fixture;

import nextstep.courses.domain.enrollment.FreeSessionEnrollment;
import nextstep.courses.domain.enrollment.PaidSessionEnrollment;
import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.courses.domain.status.RecruitmentStatus;

import static nextstep.courses.domain.status.RecruitmentStatus.*;

public class SessionEnrollmentFixture {

    public static SessionEnrollment freeSessionEnrollment() {
        return new FreeSessionEnrollment(RECRUITMENT);
    }

    public static SessionEnrollment freeSessionEnrollment(RecruitmentStatus recruitmentStatus) {
        return new FreeSessionEnrollment(recruitmentStatus);
    }

    public static SessionEnrollment paidSessionEnrollment(RecruitmentStatus recruitmentStatus, int capacity, long fee) {
        return new PaidSessionEnrollment(recruitmentStatus, capacity, fee);
    }
}
