package nextstep.courses.domain.strategy;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.code.SessionType;

public class EnrollFactory {

    public static Enrollment create(SessionType sessionType,
                                    int capacity,
                                    Amount amount) {
        if (sessionType == SessionType.FREE) {
            return create();
        }
        return create(capacity, amount);
    }

    public static Enrollment create() {
        return new FreeEnrollmentStrategy();
    }

    public static Enrollment create(int capacity,
                                    Amount amount) {
        return new PaidEnrollmentStrategy(capacity, amount);
    }
}
