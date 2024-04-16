package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.enums.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;

public class PaidSession extends Session {

    public PaidSession(Long id, SessionPeriod sessionPeriod, SessionStatus sessionStatus, int maximumCapacity, SessionFee sessionFee, CoverImage coverImage) {
        super(id
                , sessionPeriod
                , coverImage
                , SessionType.PAID
                , sessionFee
                , new ArrayList<>()
                , sessionStatus
                , maximumCapacity);
    }

    @Override
    public void enroll(NsUser user, Payment payment) {

        if (!isRecruting()) {
            throw new IllegalStateException("강의 모집중이 아닙니다.");
        }

        if (isFull()) {
            throw new IllegalStateException("최대 수강인원을 초과 하였습니다.");
        }

        if (!hasPaid(payment.amount())) {
            throw new IllegalStateException("결제금액이 수강료와 일치하지 않습니다.");
        }

        super.addStudent(user);
    }

}
