package nextstep.session.domain.policy.admiss;

import nextstep.session.domain.Session;
import nextstep.users.domain.NsUser;

public class AdmissStudentPolicy implements AdmissPolicy<Session> {
    @Override
    public void validate(Session session, NsUser loginUser, NsUser student) {
        if (session.getAdmissions().isAdmiss(student, session)) {
            throw new IllegalArgumentException("선발되지 않은 학생입니다.");
        }
    }
}
