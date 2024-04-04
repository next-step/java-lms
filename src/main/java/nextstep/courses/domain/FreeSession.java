package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUsers;

public class FreeSession extends Session {

    public FreeSession(Course course, Period period, Image image, NsUsers users) {
        super(course, period, image, users);
    }

    @Override
    public void assertCanEnroll() {
        return;
    }
}
