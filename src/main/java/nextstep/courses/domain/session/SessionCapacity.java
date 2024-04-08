package nextstep.courses.domain.session;

import nextstep.courses.exception.ExceedSessionCapacityException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionCapacity {

    private final Long id;
    private final Long sessionId;
    private final int maxCapacity;
    private final List<NsUser> users;

    public SessionCapacity(Long id, Long sessionId, int maxCapacity) throws ExceedSessionCapacityException {
        this(id, sessionId, maxCapacity, new ArrayList<>());
    }

    public SessionCapacity(Long id, Long sessionId, int maxCapacity, List<NsUser> users) throws ExceedSessionCapacityException {
        validateCapacity(maxCapacity, users);
        this.id = id;
        this.sessionId = sessionId;
        this.maxCapacity = maxCapacity;
        this.users = users;
    }

    private void validateCapacity(int maxCapacity, List<NsUser> users) throws ExceedSessionCapacityException {
        if (!hasCapacity(maxCapacity, users)) {
            throw new ExceedSessionCapacityException(maxCapacity, users);
        }
    }

    public boolean hasCapacity(int maxCapacity, List<NsUser> users) {
        return maxCapacity >= users.size();
    }

    public boolean hasCapacity() {
        return maxCapacity > getCurrentCapacity();
    }

    public void addUser(NsUser user) throws ExceedSessionCapacityException {
        if (!hasCapacity()) {
            throw new ExceedSessionCapacityException(this);
        }
        users.add(user);
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return users.size();
    }
}
