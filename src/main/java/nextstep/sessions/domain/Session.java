package nextstep.sessions.domain;

import nextstep.images.domain.Image;
import nextstep.users.domain.NsUser;

public class Session {

    private final Long id;
    private final Subject subject;
    private final Enrollment enrollment;
    private final SessionPeriod sessionPeriod;
    private final Image image;
    private final SessionType sessionType;

    public Session(Long id, Subject subject, Enrollment enrollment, SessionPeriod sessionPeriod, Image image, SessionType sessionType) {
        this.id = id;
        this.subject = subject;
        this.enrollment = enrollment;
        this.sessionPeriod = sessionPeriod;
        this.image = image;
        this.sessionType = sessionType;
    }

    public void enroll(NsUser user, int payMoney) {
        enrollment.enroll(user, payMoney);
    }

    public void recruiting() {
        this.enrollment.recruiting();
    }
}
