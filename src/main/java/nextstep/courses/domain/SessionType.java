package nextstep.courses.domain;

public interface SessionType {

    static SessionType determineSessionType(boolean isPaid, Long sessionTypeId, Integer maxStudents, Integer sessionFee) {
        if (isPaid) {
            return new PaidSession(sessionTypeId, maxStudents, sessionFee);
        }
        return new FreeSession(sessionTypeId);
    }

    static SessionType determineSessionTypeByDB(Long freeSessionTypeId, Long paidSessionTypeId, Integer maxStudents, Integer sessionFee) {
        if (freeSessionTypeId == null) {
            return new PaidSession(paidSessionTypeId, maxStudents, sessionFee);
        }
        return new FreeSession(freeSessionTypeId);
    }

    boolean isWithinCapacity(Integer size);
    boolean checkSessionFeeEquality(Integer sessionFee);
}
