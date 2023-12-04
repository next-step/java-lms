package nextstep.lms.domain;

import nextstep.lms.dto.EnrollApplicationDTO;

public class SessionInfo {
    private final SessionDetail sessionDetail;
    private final SessionMetadata sessionMetadata;

    public SessionInfo(SessionDetail sessionDetail, SessionMetadata sessionMetadata) {
        this.sessionDetail = sessionDetail;
        this.sessionMetadata = sessionMetadata;
    }

    public void enroll(Students students, EnrollApplicationDTO enrollApplicationDTO) {
        sessionMetadata.sessionStatusCheck();
        sessionDetail.enroll(students, enrollApplicationDTO);
    }
}
