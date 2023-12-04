package nextstep.lms.domain;

import nextstep.lms.dto.EnrollApplicationDTO;

public class SessionDetail {
    private final SessionEnrollmentManagement sessionEnrollmentManagement;
    private final SessionPeriod sessionPeriod;

    public SessionDetail(SessionEnrollmentManagement sessionEnrollmentManagement, SessionPeriod sessionPeriod) {
        this.sessionEnrollmentManagement = sessionEnrollmentManagement;
        this.sessionPeriod = sessionPeriod;
    }

    public void enroll(Students students, EnrollApplicationDTO enrollApplicationDTO) {
        sessionEnrollmentManagement.enroll(students, enrollApplicationDTO);
    }
}
