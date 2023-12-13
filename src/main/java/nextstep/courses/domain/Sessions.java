package nextstep.courses.domain;


import java.util.List;
import nextstep.users.domain.NsUser;

public class Sessions {
    private final List<Session> sessionList;

    public Sessions(final List<Session> sessionList){
        this.sessionList = sessionList;
    }

    public void enroll(NsUser user, Long sessionId){
        Session session = this.sessionList.stream().filter(e -> e.isSameId(sessionId)).findFirst().orElseThrow(
                ()->new IllegalArgumentException(ExceptionMessage.SESSIONS_NOT_FOUND_SESSION.getMessage()));
        session.enroll(user);
    }
}
