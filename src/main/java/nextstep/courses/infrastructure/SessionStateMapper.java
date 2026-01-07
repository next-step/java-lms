package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SessionStstus;
import nextstep.courses.domain.session.SessionProgress;

public class SessionStateMapper {

    public static SessionStstus toEnrollmentStatus(String enrollmentStatus, String legacyState) {

        if (enrollmentStatus != null) {
            return SessionStstus.valueOf(enrollmentStatus);
        }

        if ("OPEN".equals(legacyState)) {
            return SessionStstus.OPEN;
        }

        return SessionStstus.CLOSED;
    }

    public static SessionProgress toProgress(String progressStatus, String legacyState) {

        if (progressStatus != null) {
            return SessionProgress.valueOf(progressStatus);
        }

        if ("READY".equals(legacyState)) {
            return SessionProgress.READY;
        }

        if ("OPEN".equals(legacyState)) {
            return SessionProgress.IN_PROGRESS;
        }

        return SessionProgress.FINISHED;
    }
}
