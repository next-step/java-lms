package nextstep.lms.domain;

import nextstep.lms.dto.EnrollApplicationDTO;

import java.time.LocalDateTime;

public class SessionDetail {
    private final SessionEnrollmentManagement sessionEnrollmentManagement;
    private final SessionPeriod sessionPeriod;

    public SessionDetail(SessionEnrollmentManagement sessionEnrollmentManagement, SessionPeriod sessionPeriod) {
        this.sessionEnrollmentManagement = sessionEnrollmentManagement;
        this.sessionPeriod = sessionPeriod;
    }

    public void enroll(Students students, EnrollApplicationDTO enrollApplicationDTO) {
        if (sessionPeriod.canEnroll(LocalDateTime.now())) {
            sessionEnrollmentManagement.enroll(students, enrollApplicationDTO);
        }
    }

    public void sessionStatusCheck() {
        sessionEnrollmentManagement.sessionStatusCheck();
    }
}
