package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUsers;

public class FreeSession extends Session {

    public FreeSession(Course course, Period period, Image image, NsUsers users) {
        super(course, period, image, users,Type.FREE);
    }

    public FreeSession(Long idx, Course course, Period period, Image image, Status status, NsUsers nsUsers) {
        super(idx, course, period, image, status, nsUsers, Type.FREE);
    }

    @Override
    public void assertCanEnroll() {
        return;
    }
}
