package nextstep.courses.domain.strategy;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.code.EnrollmentType;

public class EnrollmentFactory {

    private EnrollmentFactory() {
    }

    public static EnrollmentStrategy create(long id,
                                            long sessionId,
                                            String sessionType,
                                            int capacity,
                                            long amount) {
        return create(id, sessionId, EnrollmentType.valueOf(sessionType), capacity, new Amount(amount));
    }

    public static EnrollmentStrategy create(long id,
                                            long sessionId,
                                            EnrollmentType sessionType,
                                            int capacity,
                                            Amount amount) {
        if (sessionType == EnrollmentType.FREE) {
            return create(id, sessionId);
        }
        return create(id, sessionId, capacity, amount);
    }

    private static EnrollmentStrategy create(long id,
                                             long sessionId) {
        return new FreeEnrollmentStrategy(id, sessionId);
    }

    private static EnrollmentStrategy create(long id,
                                             long sessionId,
                                             int capacity,
                                             Amount amount) {
        return new PaidEnrollmentStrategy(id, sessionId, capacity, amount);
    }

}
