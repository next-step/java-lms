package nextstep.courses.domain;

import nextstep.courses.CannotEnrollSessionException;
import nextstep.courses.domain.vo.CoverImage;
import nextstep.courses.domain.vo.Period;
import nextstep.courses.domain.vo.Price;
import nextstep.users.domain.NsUser;

class PaySession extends Session {

    private final Price price;

    public PaySession(Period period, CoverImage coverImage, Attendees attendees, Price price, SessionState sessionState) {
        super(period, coverImage, attendees, sessionState);
        this.price = price;
    }

    @Override
    void enroll(NsUser nsUser, ChargeStrategy chargeStrategy) {
        if (!recruiting()) {
            throw new CannotEnrollSessionException("강의 상태가 모집중이 아닙니다");
        }
        if (!chargeStrategy.isPaid(price)) {
            throw new CannotEnrollSessionException("강의료와 결제금액 불일치");
        }

        attendees.add(nsUser);
    }
}
