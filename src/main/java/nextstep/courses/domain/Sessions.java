package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private final List<Session> sessionList = new ArrayList<>();

    public Sessions() {

    }

    public void add(Session session) {
        this.sessionList.add(session);
    }

}
