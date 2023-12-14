package nextstep.courses.domain;


import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Sessions {
    private final List<Session> sessionList = new ArrayList<>();

    public Sessions(){}

    public Sessions(List<Session> sessionList){
        this.sessionList.clear();
        this.sessionList.addAll(sessionList);
    }

    public void enroll(NsUser user, Long sessionId){
        Session session = this.sessionList.stream().filter(e -> e.isSameId(sessionId)).findFirst().orElseThrow(
                ()->new IllegalArgumentException(ExceptionMessage.SESSIONS_NOT_FOUND_SESSION.getMessage()));
        session.enroll(user);
    }
}
