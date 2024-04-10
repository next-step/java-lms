package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.SessionCapacity;
import nextstep.users.domain.NsUser;

import java.util.List;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_CAPACITY_ID;
import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;

public class SessionCapacityFixture {

    public static final int MAX_CAPACITY = 10;

    public static SessionCapacity sessionCapacity(int maxCapacity, List<NsUser> users) {
        return new SessionCapacity(SESSION_CAPACITY_ID, SESSION_ID, maxCapacity, users);
    }

    public static SessionCapacity sessionCapacity(int maxCapacity) {
        return new SessionCapacity(SESSION_CAPACITY_ID, SESSION_ID, maxCapacity);
    }

    public static SessionCapacity sessionCapacity() {
        return new SessionCapacity(SESSION_CAPACITY_ID, SESSION_ID, MAX_CAPACITY);
    }

}
