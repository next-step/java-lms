package nextstep.courses.domain;


import java.util.List;
import nextstep.users.domain.NsUser;

public class Sessions {
    private final List<Session> sessionList;

    public Sessions(final List<Session> sessionList){
        this.sessionList = sessionList;
    }

    public void enroll(NsUser user, Long sessionId, Long amountOfPaid){
        Session session = this.sessionList.stream().filter(e -> e.isSameId(sessionId)).findFirst().orElseThrow(
                IllegalArgumentException::new);
        session.enroll(user, amountOfPaid);
    }
}
