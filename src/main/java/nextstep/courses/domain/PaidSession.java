package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.courses.exception.PaidSessionException;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;

public class PaidSession extends Session {

    private Amount sessionFee;
    private int maxApplyCount;

    public PaidSession(long id, CoverImage image, LocalDate start, LocalDate end, SessionState state, long fee, int max) {
        super(id, image, start, end, state);
        this.sessionFee = new Amount(fee);
        this.maxApplyCount = max;
    }

    @Override
    public void apply(Payment payment) {
        validateState();
        //Todo : payment의 역할이 맞을까 -> 해당 객체 가격을 vo로 만들고 싶자만 패키지가 다르다
        //하지만 반대로 Amount객체의 역할로 하면 getter를 써야한다....
        validateFee(payment);
        participants.isMaxCount(maxApplyCount);
        this.participants = participants.add(payment.findUser());
    }


    private void validateFee(Payment payment) {
        if (payment.isNotSameAmount(sessionFee)) {
            throw new PaidSessionException("수강생이 결제한 금액과 수강료가 일치하지 않습니다");
        }
    }


}
