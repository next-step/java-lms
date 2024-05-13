package nextstep.courses.domain.session;

import nextstep.courses.CanNotApplyException;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.Image.Image;

public class FreeSession extends Session{
    private static final int FREE_COST = 0;

    public FreeSession(Image image, Period period, SessionStatus status, Course course) {
        super(image, period, status, course);
    }

    @Override
    public boolean isAppliable(int pay, int order) throws CanNotApplyException {
        validPay(pay);
        return true;
    }

    private static void validPay(int pay) throws CanNotApplyException {
        if(pay != FREE_COST){
            throw new CanNotApplyException("무료 강의입니다.");
        }
    }
}
