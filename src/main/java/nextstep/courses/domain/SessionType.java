package nextstep.courses.domain;

public abstract class SessionType {

    public SessionType() {
    }

    public static SessionType determineSessionType(boolean isPaid, Integer maxStudents, Integer sessionFee) {
        if (isPaid) {
            return new PaidSession(maxStudents, sessionFee);
        }
        return new FreeSession();
    }

    public abstract boolean isWithinCapacity(Integer size);
    public abstract boolean checkSessionFeeEquality(Integer sessionFee);
}
