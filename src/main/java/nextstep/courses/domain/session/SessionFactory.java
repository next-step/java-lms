package nextstep.courses.domain.session;

public abstract class SessionFactory {

    public static Session create(long sessionId, long courseId, SessionBody sessionBody, SessionEnrollment sessionEnrollment, long fee, int maxEnrollments) {
        if (isPaidSession(fee, maxEnrollments)) {
            return new PaidSession(sessionId, courseId, sessionBody, sessionEnrollment, fee, maxEnrollments);
        }
        return new FreeSession(sessionId, courseId, sessionBody, sessionEnrollment);
    }

    private static boolean isPaidSession(long fee, int maxEnrollments) {
        return fee != 0 && maxEnrollments != 0;
    }

}
