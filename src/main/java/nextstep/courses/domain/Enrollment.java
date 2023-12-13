package nextstep.courses.domain;

import java.util.ArrayList;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class Enrollment {
    private final NsUsers users;
    private final NsUserLimit limits;

    public Enrollment() {
        this((new NsUsers(new ArrayList<>())), new NsUserLimit(1,SessionPaymentType.PAID));
    }

    public Enrollment(NsUsers users, NsUserLimit limit) {
        validateCounts(users, limit);
        this.limits = limit;
        this.users = users;
    }

    public boolean isFull() {
        return users.isFull(limits);
    }

    private void validateCounts(NsUsers users, NsUserLimit limits) {
        if (users.isGreater(limits)) {
            throw new IllegalArgumentException(ExceptionMessage.ENROLLMENT_SIZE.getMessage());
        }
    }

    public void enroll(NsUser nsUser) {
        if(users.isFull(limits)){
            throw new IllegalArgumentException(ExceptionMessage.ENROLLMENT_SIZE.getMessage());
        }
        users.add(nsUser);
    }
}