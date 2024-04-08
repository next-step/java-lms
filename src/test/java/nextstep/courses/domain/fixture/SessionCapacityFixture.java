package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.SessionCapacity;
import nextstep.courses.exception.ExceedSessionCapacityException;
import nextstep.users.domain.NsUser;

import java.util.List;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_CAPACITY_ID;
import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;

public class SessionCapacityFixture {

    public static final int MAX_CAPACITY = 10;

    public static SessionCapacity sessionCapacity(int maxCapacity, List<NsUser> users) throws ExceedSessionCapacityException {
        return new SessionCapacity(SESSION_CAPACITY_ID, SESSION_ID, maxCapacity, users);
    }

    public static SessionCapacity sessionCapacity(int maxCapacity) throws ExceedSessionCapacityException {
        return new SessionCapacity(SESSION_CAPACITY_ID, SESSION_ID, maxCapacity);
    }

    public static SessionCapacity sessionCapacity() throws ExceedSessionCapacityException {
        return new SessionCapacity(SESSION_CAPACITY_ID, SESSION_ID, MAX_CAPACITY);
    }

}
