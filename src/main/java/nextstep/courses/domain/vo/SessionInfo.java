package nextstep.courses.domain.vo;

import nextstep.users.domain.NsUser;

public class SessionInfo {

    private String title;

    private NsUser instructor;

    public SessionInfo() {
    }

    public SessionInfo(String title,
                       NsUser instructor) {
        this.title = title;
        this.instructor = instructor;
    }
}
