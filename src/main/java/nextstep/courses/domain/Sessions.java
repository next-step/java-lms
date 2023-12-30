package nextstep.courses.domain;


import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.LongFunction;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class Sessions {
    private final Set<Session> sessionSet;

    public Sessions(){
        sessionSet = new LinkedHashSet<>();
    }

    public Sessions(Set<Session> sessionSet){
        this.sessionSet = sessionSet;
    }

    public Session enroll(NsUser user, Long sessionId){
        Session session = this.sessionSet.stream().filter(e -> e.isSameId(sessionId)).findFirst().orElseThrow(
                ()->new IllegalArgumentException(ExceptionMessage.SESSIONS_NOT_FOUND_SESSION.getMessage()));
        session.enroll(user);
        return session;
    }

    public void add(Session session){
        if(sessionSet.contains(session)){
            throw new IllegalArgumentException("이미 중복된 강의를 포함하고 있습니다.");
        }
        sessionSet.add(session);
    }

    public void addAll(Sessions sessions){
        this.sessionSet.addAll(sessions.sessionSet);
    }

    public void replaceAllEnrollment(LongFunction<NsUsers> function){
        for (Session session : sessionSet) {
            session.replaceEnrollmentNsUsers(function);
        }
    }
}
