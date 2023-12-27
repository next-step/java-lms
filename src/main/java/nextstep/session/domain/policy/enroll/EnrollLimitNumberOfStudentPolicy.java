package nextstep.session.domain.policy.enroll;

import nextstep.session.domain.PaidSession;
import nextstep.users.domain.NsUser;

public class EnrollLimitNumberOfStudentPolicy implements EnrollPolicy<PaidSession> {
    @Override
    public void validate(PaidSession session, NsUser nsUser) {
        if (session.isFull()) {
            throw new IllegalStateException("정원이 초과되어 참여할 수 없습니다.");
        }
    }
}
