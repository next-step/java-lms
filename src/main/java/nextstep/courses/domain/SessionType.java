package nextstep.courses.domain;

public class SessionType {

    private final boolean isPaid;

    public SessionType(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public static SessionType determineSessionType(boolean isPaid, Integer maxStudents, Integer sessionFee) {
        if (isPaid) {
            return new PaidSession(isPaid, maxStudents, sessionFee);
        }
        return new FreeSession(isPaid);
    }
}
