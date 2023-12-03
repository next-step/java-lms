package nextstep.lms.domain;

import nextstep.lms.dto.EnrollApplicationDTO;

public class Session {
    private final SessionInfo sessionInfo;
    private final Students students;

    public Session(SessionInfo sessionInfo, Students students) {
        this.sessionInfo = sessionInfo;
        this.students = students;
    }

    public void enroll(EnrollApplicationDTO enrollApplicationDTO) {
        sessionInfo.enroll(this.students, enrollApplicationDTO);
    }
}
