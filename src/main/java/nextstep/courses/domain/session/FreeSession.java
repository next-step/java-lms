package nextstep.courses.domain.session;

import nextstep.courses.domain.SystemTimeStamp;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FreeSession extends Session {

    public static FreeSession valueOf(long id, String title, long courseId, EnrollmentStatus enrollmentStatus
            , LocalDate startDate, LocalDate endDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new FreeSession(id, title, courseId, SessionType.FREE
                , new SessionPlan(enrollmentStatus, startDate, endDate)
                , new SystemTimeStamp(createdAt, updatedAt));
    }

    public FreeSession(Long id, String title, long courseId, SessionType sessionType, SessionPlan sessionPlan, SystemTimeStamp systemTimeStamp) {
        super(id, title, courseId, sessionType, sessionPlan, systemTimeStamp);
    }

    @Override
    public void signUp(NsUser student) {
        super.signUp(student);
    }
}
