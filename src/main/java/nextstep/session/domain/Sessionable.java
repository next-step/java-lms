package nextstep.session.domain;

import nextstep.users.domain.NsUser;

public interface Sessionable {

    void enroll(NsUser user);
    void changeStatus(SessionStatus status);
    int enrolledNumber();
}
