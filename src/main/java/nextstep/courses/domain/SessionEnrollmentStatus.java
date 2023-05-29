package nextstep.courses.domain;

import java.util.Map;

public enum SessionEnrollmentStatus {
    ENROLLMENT("모집"),
    NON_ENROLLMENT("비모집"),
    NO_INFO("없음");
    private final String enrollmentStatus;

    private static final Map<String, SessionEnrollmentStatus> sessionEnrollmentStatusMap = Map.of(ENROLLMENT.name(), ENROLLMENT, NON_ENROLLMENT.name(), NON_ENROLLMENT);

    public static SessionEnrollmentStatus find(String enrollmentStatus) {
        if (enrollmentStatus == null) {
            return NO_INFO;
        }
        return sessionEnrollmentStatusMap.get(enrollmentStatus);
    }

    public boolean canEnrollment() {
        return this != NON_ENROLLMENT;
    }

    SessionEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }
}
