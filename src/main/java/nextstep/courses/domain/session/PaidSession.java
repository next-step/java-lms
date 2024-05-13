package nextstep.courses.domain.session;

import nextstep.courses.CanNotApplyException;
import nextstep.courses.domain.Image.Image;

public class PaidSession extends Session {

    private final int fee;
    private final int limit;

    public PaidSession(Image image, Period period, SessionStatus status, int fee, int limit) {
        super(image, period, status);
        this.fee = fee;
        this.limit = limit;
    }

    @Override
    public boolean isAppliable(int pay, int order) throws CanNotApplyException {
        validPay(pay);
        validStudentsLimit(order);
        return fee == pay && order <= limit;
    }

    private void validStudentsLimit(int order) throws CanNotApplyException {
        if(order > limit){
            throw new CanNotApplyException("수강생이_꽉_찼습니다");
        }
    }

    private void validPay(int pay) throws CanNotApplyException {
        if(pay != fee){
            throw new CanNotApplyException("수강료를 잘 못 내셨습니다.");
        }
    }
}
