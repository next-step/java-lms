package nextstep.Session;

import nextstep.users.domain.NsUser;

public class Session {

    private Long id;

    private String title;

    private SessionStatus sessionStatus;

    private SessionPricing sessionPricing;

    private Image coverImage;

    private SessionDuration sessionDuration;

    private Participants participants = new Participants();

    private Session() {

    }

    public static Session createFreeSession(final String title, final Image coverImage, final SessionDuration sessionDuration) {
        Session session = new Session();
        session.title = title;
        session.sessionStatus = SessionStatus.PREPARE;
        session.sessionPricing = new SessionPricing(new SessionPrice());
        session.coverImage = coverImage;
        session.sessionDuration = sessionDuration;
        return session;
    }

    public static Session createPaidSession(final String title, final Image coverImage, final SessionDuration sessionDuration, int sessionPrice, int maxParticipants) {
        Session session = new Session();
        session.title = title;
        session.sessionStatus = SessionStatus.PREPARE;
        session.sessionPricing = new SessionPricing(new SessionPrice(sessionPrice), maxParticipants);
        session.coverImage = coverImage;
        session.sessionDuration = sessionDuration;
        return session;
    }

    public Participants getParticipants() {
        return participants;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void enrollStudent(final NsUser nsUser, final int payment) {
        validateSessionRecruit();
        int currentParticipants = participants.count();
        if (sessionPricing.isFreeSession()) {
            throw new IllegalArgumentException("이 강의는 무료 강의입니다. 결제 정보가 필요하지 않습니다.");
        }
        sessionPricing.canEnroll(payment, currentParticipants);
        participants.addStudent(nsUser);
    }

    public void enrollStudent(final NsUser nsUser) {
        validateSessionRecruit();
        if (!sessionPricing.isFreeSession()) {
            throw new IllegalArgumentException("이 강의는 유료 강의입니다. 결제 금액이 필요 합니다.");
        }
        participants.addStudent(nsUser);
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
