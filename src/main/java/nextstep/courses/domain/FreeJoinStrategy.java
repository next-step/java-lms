package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class FreeJoinStrategy implements JoinStrategy{
    @Override
    public boolean joinable(Session session, Payment pay){
        return session.recruiting() && pay.isFree();
    }
}
