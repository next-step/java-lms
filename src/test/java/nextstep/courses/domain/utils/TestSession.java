package nextstep.courses.domain.utils;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionDate;
import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.RecruitStatus;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.List;

public class TestSession extends Session {
    public TestSession(Long id, SessionImage sessionImage, RecruitStatus recruitStatus, SessionDate sessionDate) {
        super(id, List.of(sessionImage), recruitStatus, sessionDate);
    }

    public TestSession(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionDate sessionDate) {
        super(id, sessionImage, recruitStatus, sessionDate);
    }

    @Override
    protected void assertSatisfiedCondition(NsUser user, Payment payment) {

    }
}