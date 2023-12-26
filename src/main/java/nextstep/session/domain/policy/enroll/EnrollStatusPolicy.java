package nextstep.session.domain.policy.enroll;

import nextstep.session.domain.Session;
import nextstep.users.domain.NsUser;

public class EnrollStatusPolicy implements EnrollPolicy<Session> {
    @Override
    public void validate(Session session, NsUser nsUser) {
        if (session.getSessionStatus().isEnd()) {
            throw new IllegalStateException("종료된 세션에는 참여할 수 없습니다.");
        }
    }
}
