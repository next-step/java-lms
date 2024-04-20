package nextstep.courses.domain.fixture;

import nextstep.courses.domain.enrollment.SessionCapacity;

public class SessionCapacityFixture {

    public static final int MAX_CAPACITY = 10;

    public static SessionCapacity sessionCapacity(int maxCapacity) {
        return new SessionCapacity(maxCapacity);
    }

    public static SessionCapacity sessionCapacity() {
        return new SessionCapacity(MAX_CAPACITY);
    }

}
