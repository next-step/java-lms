package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.enums.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FreeSession extends Session {

    public FreeSession(Long id, SessionPeriod sessionPeriod, SessionStatus sessionStatus, CoverImage coverImage, List<NsUser> students) {
        super(id
                , sessionPeriod
                , coverImage
                , SessionType.FREE
                , new SessionFee(new BigDecimal(0))
                , new ArrayList<>(students)
                , sessionStatus);
    }

    @Override
    public void enroll(NsUser user, Payment payment) {
        validateRecruting();
        validateEnrolled(user, this);
        super.addStudent(user);
    }
}
