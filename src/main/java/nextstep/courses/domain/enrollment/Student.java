package nextstep.courses.domain.enrollment;

import nextstep.users.domain.NsUser;

public class Student {

    private Long sessionId;
    private Long nsUserId;

    public static Student from(Long sessionId, NsUser nsUser) {
        return new Student(sessionId, nsUser.getId());
    }

    public Student(Long sessionId, Long nsUserId) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

}
