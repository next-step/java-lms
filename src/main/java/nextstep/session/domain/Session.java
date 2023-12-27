package nextstep.session.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Session {

    private Long id;

    private String title;

    private SessionStatus sessionStatus;

    private SessionType sessionType;

    private Image coverImage;

    private SessionDuration sessionDuration;

    private Enrollment enrollment;

    private Session() {

    }

    public static Session createFreeSession(final String title, final Image coverImage,
        final SessionDuration sessionDuration) {
        Session session = new Session();
        session.title = title;
        session.sessionStatus = SessionStatus.PREPARE;
        session.coverImage = coverImage;
        session.sessionDuration = sessionDuration;
        session.sessionType = SessionType.FREE;
        session.enrollment = new Enrollment(new SessionPricing(new SessionPrice()),
            new Participants());
        return session;
    }

    public static Session createPaidSession(final String title, final Image coverImage,
        final SessionDuration sessionDuration, int sessionPrice, int maxParticipants) {
        Session session = new Session();
        session.title = title;
        session.sessionStatus = SessionStatus.PREPARE;
        session.coverImage = coverImage;
        session.sessionDuration = sessionDuration;
        session.sessionType = SessionType.PAID;
        session.enrollment = new Enrollment(new SessionPricing(new SessionPrice(sessionPrice), maxParticipants),
            new Participants());

        return session;
    }

    public Participants getParticipants() {
        return enrollment.getParticipants();
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void enrollStudent(final NsUser nsUser, final Payment payment) {
        validateSessionRecruit();

        if (sessionType == SessionType.FREE) {
            enrollment.enrollStudent(nsUser);
        }

        if (sessionType == SessionType.PAID) {
            enrollment.enrollStudent(nsUser, payment);
        }
    }

    private void validateSessionRecruit() {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException("해당 강의는 현재 모집중이 아닙니다.");
        }
    }

    public void changeToRecruit() {
        this.sessionStatus = SessionStatus.RECRUITING;
    }

    public void changeToFinish() {
        this.sessionStatus = SessionStatus.FINISHED;
    }

}
