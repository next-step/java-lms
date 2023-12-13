package nextstep.courses.domain;

import java.util.ArrayList;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class Enrollment {
    private final NsUsers users;
    private final Integer limits;

    public Enrollment() {
        this((new NsUsers(new ArrayList<>())), 1);
    }

    public Enrollment(NsUsers users, Integer limits) {
        validateCounts(users, limits);
        this.limits = limits;
        this.users = users;
    }

    public boolean isNotEmpty() {
        return users.isNotEmpty();
    }

    public boolean isFull() {
        return users.isFull(limits);
    }

    private void validateCounts(NsUsers users, Integer limits) {
        if (users.isGreater(limits)) {
            throw new IllegalArgumentException();
        }
    }

    public void enroll(NsUser nsUser) {
        if(users.isFull(limits)){
            throw new IllegalArgumentException();
        }
        users.add(nsUser);
    }
}
