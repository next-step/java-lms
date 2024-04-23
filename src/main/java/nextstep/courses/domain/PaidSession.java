package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.enums.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class PaidSession extends Session {

    public PaidSession(Long id, SessionPeriod sessionPeriod, SessionStatus sessionStatus, int maximumCapacity, SessionFee sessionFee, CoverImage coverImage, List<NsUser> students) {
        super(id
                , sessionPeriod
                , coverImage
                , SessionType.PAID
                , sessionFee
                , new ArrayList<>(students)
                , sessionStatus
                , maximumCapacity);
    }

    @Override
    public void enroll(NsUser user, Payment payment) {

        validateRecruting();
        validateEnrolled(user, this);
        validateMaxStudent();
        validatePaid(payment);

        super.addStudent(user);
    }

    private void validateMaxStudent() {
        if (isFull()) {
            throw new IllegalStateException("최대 수강인원을 초과 하였습니다.");
        }
    }

    private void validatePaid(Payment payment) {
        if (!hasPaid(payment.amount())) {
            throw new IllegalStateException("결제금액이 수강료와 일치하지 않습니다.");
        }
    }

}
