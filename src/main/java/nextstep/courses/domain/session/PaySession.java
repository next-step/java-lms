package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.exception.NotMatchAmountException;
import nextstep.courses.exception.NotRegisterSession;
import nextstep.users.domain.NsUser;

public class PaySession extends Session {

    private Amount amount;
    private int limit;

    public PaySession(Long id, PayType payType, Status status, CoverImage coverImage, Amount amount, int limit) {
        super(id, payType, status, coverImage);
        this.amount = amount;
        this.limit = limit;
    }

    @Override
    public void register(NsUser student) throws NotRegisterSession {
        sessionStudents.validateLimit(limit);
        sessionStudents.add(this, student);
    }

    @Override
    public void isEqual(Long amount) throws NotMatchAmountException {
        this.amount.validate(amount);
    }
}
