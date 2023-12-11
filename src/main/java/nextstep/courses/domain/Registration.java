package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Registration {

    public Registration() {
    }

    public void register(NsUser user, Session session, Long amount) {
        session.enroll(user, amount);
    }
}
