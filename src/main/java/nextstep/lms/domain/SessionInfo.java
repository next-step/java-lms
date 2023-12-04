package nextstep.lms.domain;

import nextstep.lms.dto.EnrollApplicationDTO;

public class SessionInfo {
    private final CoverImage coverImage;
    private final SessionDetail sessionDetail;

    public SessionInfo(CoverImage coverImage, SessionDetail sessionDetail) {
        this.coverImage = coverImage;
        this.sessionDetail = sessionDetail;
    }

    public void enroll(Students students, EnrollApplicationDTO enrollApplicationDTO) {
        sessionDetail.sessionStatusCheck();
        sessionDetail.enroll(students, enrollApplicationDTO);
    }
}
