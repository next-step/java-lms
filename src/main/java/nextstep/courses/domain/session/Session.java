package nextstep.courses.domain.session;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.users.domain.NsUser;

import java.util.Optional;

public class Session {

    private Long id;

    private SessionDuration sessionDuration;

    private CoverImage coverImage;

    private SessionEnrollment sessionEnrollment;

    public void enrollSession(NsUser student, Long userPay) {
        if (sessionEnrollment.isFree()) {
            sessionEnrollment.enrollFreeSession(student);
        }
        sessionEnrollment.enrollPaySession(student, userPay);
    }
}
