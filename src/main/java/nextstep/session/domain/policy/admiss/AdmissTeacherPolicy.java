package nextstep.session.domain.policy.admiss;

import nextstep.session.domain.Session;
import nextstep.users.domain.NsUser;

public class AdmissTeacherPolicy implements AdmissPolicy<Session> {
    @Override
    public void validate(Session session, NsUser loginUser, NsUser student) {
        if (!session.isTeacher(loginUser)) {
            throw new IllegalArgumentException("강사만 승인할 수 있습니다.");
        }
    }
}
