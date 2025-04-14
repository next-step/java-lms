package nextstep.courses.domain;

public class PaidJoinStrategy implements JoinStrategy {
    @Override
    public boolean joinable(Session session, long payAmount) {
        return session.recruiting() &&
                session.underCapacity() &&
                session.tuitionMatched(payAmount);
    }
}
