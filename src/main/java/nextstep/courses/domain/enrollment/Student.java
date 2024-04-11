package nextstep.courses.domain.enrollment;

import nextstep.users.domain.NsUser;

public class Student {

    private Long id;
    private Long nsUserId;

    public static Student from(NsUser nsUser) {
        return new Student(nsUser.getId());
    }

    public Student(Long nsUserId) {
        this.nsUserId = nsUserId;
    }

}
