package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Student {
    private final NsUser user;

    public Student(NsUser user) {
        this.user = user;
    }
}
