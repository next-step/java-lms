package nextstep.users.domain;

import nextstep.courses.domain.Session;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class NsTeacher extends NsUser{

    private Set<Long> sessionList;

    public NsTeacher(){
        super();
        this.sessionList = new HashSet<>();
    }

    public NsTeacher(Long id, String userId, String password, String name, String email){
        super(id, userId, password, name, email, LocalDateTime.now(), null);
        this.sessionList = new HashSet<>();
    }

    public Set<Long> getSessionList() {
        return sessionList;
    }

    public Set<Long> sessionList(){
        return sessionList;
    }

    public void chargeSession(Long sessionId){
        sessionList.add(sessionId);
    }

    public boolean hasSession(Long sessionId) {
        return sessionList.contains(sessionId);
    }
}
