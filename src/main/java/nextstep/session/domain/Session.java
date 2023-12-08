package nextstep.session.domain;

import java.util.List;
import nextstep.image.domain.Image;
import nextstep.session.exception.SessionOutOfDateException;
import nextstep.users.domain.NsUser;

public class Session {

    public static final String SESSION_OUT_OF_DATE_EXCEPTION = "강의가 시작되어 수강 신청할 수 없습니다.";

    private SessionPeriod sessionPeriod;

    private Image image;

    private SessionType type;

    private SessionStatus status;

    private Enrollment enrollment;

    private long price;

    public Session() {
    }

    public Session(SessionPeriod sessionPeriod, Image image, SessionType type, SessionStatus status, long price) {
        this.sessionPeriod = sessionPeriod;
        this.image = image;
        this.type = type;
        this.status = status;
        this.price = price;
    }

    public Session(SessionPeriod sessionPeriod, Image image, SessionType type, SessionStatus status,
                   Enrollment enrollment,
                   long price) {
        this.sessionPeriod = sessionPeriod;
        this.image = image;
        this.type = type;
        this.status = status;
        this.enrollment = enrollment;
        this.price = price;
    }

    public void enrollSession(long fee, NsUser user) {
        checkSessionIsStart();
        if (type.isPaid()) {
            enrollment.enrollPaidSession(fee, user);
            return;
        }
        enrollment.enrollFreeSession(user);
    }

    private void checkSessionIsStart() {
        if (sessionPeriod.isStart(new CurrentPeriodStrategy())) {
            throw new SessionOutOfDateException(SESSION_OUT_OF_DATE_EXCEPTION);
        }
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    public Image getImage() {
        return image;
    }

    public SessionType getType() {
        return type;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public long getPrice() {
        return price;
    }
}
