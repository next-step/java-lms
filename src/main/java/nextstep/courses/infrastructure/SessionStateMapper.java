package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.EnrollmentStatus;
import nextstep.courses.domain.session.SessionProgress;

public class SessionStateMapper {

    public static EnrollmentStatus toEnrollmentStatus(String enrollmentStatus, String legacyState) {

        if (enrollmentStatus != null) {
            return EnrollmentStatus.valueOf(enrollmentStatus);
        }

        if ("OPEN".equals(legacyState)) {
            return EnrollmentStatus.OPEN;
        }

        return EnrollmentStatus.CLOSED;
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
