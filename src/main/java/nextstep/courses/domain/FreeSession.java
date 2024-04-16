package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.enums.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;

public class FreeSession extends Session {

    public FreeSession(Long id, SessionPeriod sessionPeriod, SessionStatus sessionStatus, CoverImage coverImage) {
        super(id
                , sessionPeriod
                , coverImage
                , SessionType.FREE
                , new SessionFee(0)
                , new ArrayList<>()
                , sessionStatus
                , Integer.MAX_VALUE);
    }

    @Override
    public void enroll(NsUser user, Payment payment) {
        if (!isRecruting()) {
            throw new IllegalStateException("강의 모집중이 아닙니다.");
        }
        super.addStudent(user);
    }

}
