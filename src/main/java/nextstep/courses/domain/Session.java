package nextstep.courses.domain;

public class Session {

    private final FreeOrPaid freeOrPaid;
    private final AttendeeCount attendeeCount;
    private final SessionStatus status;
    private final Duration duration;

    public Session(boolean isFree, int maxCount, int tuition) {
        this(isFree, maxCount, tuition, SessionStatus.READY);
    }

    public Session(boolean isFree, int maxCount, int tuition, SessionStatus status) {
        this(new FreeOrPaid(isFree, tuition), new AttendeeCount(maxCount), status);
    }

    public Session(boolean isFree, int maxCount, int currentCount, int tuition) {
        this(new FreeOrPaid(isFree, tuition), new AttendeeCount(maxCount, currentCount), SessionStatus.RECRUITING);
    }

    public Session(FreeOrPaid test, AttendeeCount attendeeCount, SessionStatus status) {
        this.freeOrPaid = test;
        this.attendeeCount = attendeeCount;
        this.status = status;
        this.duration = new Duration();
    }

    public static Session freeSession() {
        return new Session(true, -1, 0);
    }

    public boolean isFrees() {
        return freeOrPaid.isFree();
    }

    public boolean hasMax() {
        return attendeeCount.limitExist();
    }

    public boolean isLeft() {
        return attendeeCount.canSignUp();
    }

    public boolean canListen(Money submit) {
        return freeOrPaid.canPay(submit);
    }

    public boolean canRegister() {
        return this.status.equals(SessionStatus.RECRUITING);
    }
}
