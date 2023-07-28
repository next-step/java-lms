package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;

public class SessionUsers {

    private final List<SessionUser> sessionUsers;
    private final int maxUserSize;
    public SessionUsers(int maxUserSize) {
        this(new ArrayList<>(), maxUserSize);
    }

    private SessionUsers(List<SessionUser> sessionUsers, int maxUserSize) {
        this.sessionUsers = sessionUsers;
        this.maxUserSize = maxUserSize;
    }


    public void enroll(SessionUser sessionUser) {
        if (sessionUsers.size() == maxUserSize) {
            throw new IllegalStateException("수강 인원이 가득 찼습니다.");
        }
        sessionUsers.add(sessionUser);
    }
}
