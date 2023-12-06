package nextstep.courses.domain;

public interface SessionType {

    static SessionType determineSessionType(boolean isPaid, Long sessionTypeId, Integer maxStudents, Integer sessionFee) {
        if (isPaid) {
            return new PaidSession(sessionTypeId, maxStudents, sessionFee);
        }
        return new FreeSession(sessionTypeId);
    }

    boolean isWithinCapacity(Integer size);
    boolean checkSessionFeeEquality(Integer sessionFee);
}
