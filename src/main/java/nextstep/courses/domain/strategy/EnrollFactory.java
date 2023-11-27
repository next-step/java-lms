package nextstep.courses.domain.strategy;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.code.EnrollType;

public class EnrollFactory {

    private EnrollFactory() {
    }

    public static EnrollmentStrategy create(long id,
                                            long sessionId,
                                            String sessionType,
                                            int capacity,
                                            long amount) {
        return create(id, sessionId, EnrollType.valueOf(sessionType), capacity, new Amount(amount));
    }

    public static EnrollmentStrategy create(long id,
                                            long sessionId,
                                            EnrollType sessionType,
                                            int capacity,
                                            Amount amount) {
        if (sessionType == EnrollType.FREE) {
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
