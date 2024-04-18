package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionFactory;
import nextstep.courses.domain.session.SessionType;
import nextstep.courses.domain.enrollment.SessionCapacity;
import nextstep.courses.domain.enrollment.SessionFee;
import nextstep.courses.domain.session.ProgressStatus;
import nextstep.courses.domain.enrollment.RecruitmentStatus;

import static nextstep.courses.domain.fixture.IdFixture.COURSE_ID;
import static nextstep.courses.domain.fixture.SessionPeriodFixture.period;

public class SessionFixture {

    public static Session freeSession() {
        return SessionFactory.get(COURSE_ID, SessionType.FREE, period(), ProgressStatus.PREPARING, RecruitmentStatus.RECRUITMENT, SessionCapacity.INFINITY, SessionFee.FREE);
    }

    public static Session paidSession(SessionType type, int capacity, long fee) {
        return SessionFactory.get(COURSE_ID, SessionType.PAID, period(), ProgressStatus.PREPARING, RecruitmentStatus.RECRUITMENT, capacity, fee);
    }

}
