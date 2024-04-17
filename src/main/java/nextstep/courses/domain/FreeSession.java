package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Set;

public class FreeSession extends Session {
    public FreeSession(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionDate sessionDate) {
        super(id, sessionImage, recruitStatus, sessionDate);
    }

    public FreeSession(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionDate sessionDate, Set<NsUser> students) {
        super(id, sessionImage, recruitStatus, sessionDate, students);
    }

    public FreeSession(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionProgressStatus sessionProgressStatus, SessionDate sessionDate, Set<NsUser> students, Set<NsUser> approveStudents) {
        super(id, sessionImage, recruitStatus, sessionProgressStatus, sessionDate, students, approveStudents);
    }

    @Override
    protected void assertSatisfiedCondition(NsUser user, Payment payment) {
    }
}
