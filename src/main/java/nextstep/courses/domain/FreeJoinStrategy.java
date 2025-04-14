package nextstep.courses.domain;

public class FreeJoinStrategy implements JoinStrategy{
    @Override
    public boolean joinable(Session session, long payAmount){
        return session.recruiting();
    }
}
