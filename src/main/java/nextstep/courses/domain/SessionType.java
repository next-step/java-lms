package nextstep.courses.domain;

public interface SessionType {

    static SessionType determineSessionType(boolean isPaid, Integer maxStudents, Integer sessionFee) {
        if (isPaid) {
            return new PaidSession(maxStudents, sessionFee);
        }
        return new FreeSession();
    }

    boolean isWithinCapacity(Integer size);
    boolean checkSessionFeeEquality(Integer sessionFee);
}
