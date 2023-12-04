package nextstep.lms.domain;

import nextstep.lms.dto.EnrollApplicationDTO;

public class SessionDetail {
    private final SessionManagement sessionManagement;
    private final SessionPeriod sessionPeriod;

    public SessionDetail(SessionManagement sessionManagement, SessionPeriod sessionPeriod) {
        this.sessionManagement = sessionManagement;
        this.sessionPeriod = sessionPeriod;
    }

    public void enroll(Students students, EnrollApplicationDTO enrollApplicationDTO) {
        sessionManagement.enroll(students, enrollApplicationDTO);
    }
}
