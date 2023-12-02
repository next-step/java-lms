package nextstep.courses.domain.entity;

import nextstep.users.domain.NsUser;

public class Enrollment {

    private NsCourse nsCourse;
    private NsUser nsUser;
    private NsSession nsSession;

    public Enrollment(NsCourse nsCourse,
                      NsUser nsUser,
                      NsSession nsSession) {
        this.nsCourse = nsCourse;
        this.nsUser = nsUser;
        this.nsSession = nsSession;
    }

    public Enrollment() {}
}
