package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionUsers {

    private List<SessionUser> values = new ArrayList<>();

    public SessionUsers() {
    }

    public SessionUsers(List<SessionUser> values) {
        this.values = values;
    }

    public SessionUser apply(Long id, Session session, NsUser user) {
        SessionUser sessionUser = new SessionUser(id, session, user);
        values.add(sessionUser);
        return sessionUser;
    }

    public boolean isClosed(RecruitmentCount recruitmentCount) {
        return values.size() >= recruitmentCount.count();
    }


}
