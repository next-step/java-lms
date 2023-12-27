package nextstep.session.domain.policy.enroll;

import nextstep.session.domain.Session;
import nextstep.users.domain.NsUser;

public class EnrollRecruitStatusPolicy implements EnrollPolicy<Session> {
    @Override
    public void validate(Session session, NsUser nsUser) {
        if (session.getSessionRecruitStatus().isClosed()) {
            throw new IllegalStateException("모집이 종료된 세션에는 참여할 수 없습니다.");
        }
    }
}
