package nextstep.courses.domain;

import nextstep.courses.CannotEnrollSessionException;
import nextstep.courses.domain.vo.CoverImage;
import nextstep.courses.domain.vo.Period;
import nextstep.courses.domain.vo.Price;
import nextstep.users.domain.NsUser;

class FreeSession extends Session {
    public FreeSession(Period period, CoverImage coverImage, SessionState sessionState) {
        super(period, coverImage, new Attendees(Integer.MAX_VALUE), sessionState);
    }

    @Override
    public void enroll(NsUser nsUser, ChargeStrategy chargeStrategy) {
        if (!recruiting()) {
            throw new CannotEnrollSessionException("강의 상태가 모집중이 아닙니다");
        }
        if (chargeStrategy.isPaid(Price.FREE)) {
            attendees.add(nsUser);
        }
    }
}
