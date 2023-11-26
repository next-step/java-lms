package nextstep.courses.domain.strategy;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.code.SessionType;

public class EnrollFactory {

    public static EnrollmentStrategy create(SessionType sessionType,
                                            int capacity,
                                            Amount amount) {
        if (sessionType == SessionType.FREE) {
            return create();
        }
        return create(capacity, amount);
    }

    public static EnrollmentStrategy create() {
        return new FreeEnrollmentStrategy();
    }

    public static EnrollmentStrategy create(int capacity,
                                            Amount amount) {
        return new PaidEnrollmentStrategy(capacity, amount);
    }
}
