package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Session {
    private final Long id;
    private final SessionDuration sessionDuration;
    private SessionEnrolmentInfo sessionEnrolmentInfo;
    private final CoverImage coverImage;

    public Session(Long id, SessionDuration sessionDuration, SessionEnrolmentInfo sessionEnrolmentInfo, CoverImage coverImage) {
        this.id = id;
        this.sessionDuration = sessionDuration;
        this.sessionEnrolmentInfo = sessionEnrolmentInfo;
        this.coverImage = coverImage;
    }

    public void enrolment(NsUser student, Long userPayed) {
        if (isFree()) {
            sessionEnrolmentInfo.freeEnrolment(student);
        }

        sessionEnrolmentInfo.payEnrolment(student, userPayed);
    }

    private boolean isFree() {
        return this.sessionEnrolmentInfo.isFree();
    }
}
