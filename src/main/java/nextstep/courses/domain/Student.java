package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Student {
    private Long id;
    private NsUser nsUser;

    public Student(Long id, NsUser nsUser) {
        this.id = id;
        this.nsUser = nsUser;
    }
}
