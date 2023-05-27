package nextstep.courses.domain;

import java.util.Map;

public enum SessionEnrollmentStatus {
    ENROLLMENT("모집"),
    NON_ENROLLMENT("비모집"),
    NO_INFO("없음(step3버전)");
    private final String enrollmentStatus;

    private static final Map<String, SessionEnrollmentStatus> sessionEnrollmentStatusMap = Map.of(ENROLLMENT.getEnrollmentStatus(), ENROLLMENT, NON_ENROLLMENT.getEnrollmentStatus(), NON_ENROLLMENT);

    public static SessionEnrollmentStatus find(String enrollmentStatus) {
        if(enrollmentStatus == null){
            return NO_INFO;
        }
        return sessionEnrollmentStatusMap.get(enrollmentStatus);
    }

    public String getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public boolean canEnrollment() {
        return this == ENROLLMENT;
    }

    SessionEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }
}
