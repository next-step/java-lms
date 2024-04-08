package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.SessionCapacity;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_CAPACITY_ID;
import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;

public class SessionCapacityFixture {

    private static final int MAX_CAPACITY = 100;

    public static SessionCapacity sessionCapacity(int maxCapacity) {
        return new SessionCapacity(SESSION_CAPACITY_ID, SESSION_ID, maxCapacity);
    }

    public static SessionCapacity sessionCapacity(int maxCapacity, int currentCapacity) {
        return new SessionCapacity(SESSION_CAPACITY_ID, SESSION_ID, maxCapacity, currentCapacity);
    }

    public static SessionCapacity sessionCapacity() {
        return new SessionCapacity(SESSION_CAPACITY_ID, SESSION_ID, MAX_CAPACITY);
    }

}
