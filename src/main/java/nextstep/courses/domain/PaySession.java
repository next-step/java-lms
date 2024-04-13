package nextstep.courses.domain;

import nextstep.courses.domain.exception.LackPointException;
import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaySession extends Session {

    private int maximumStudents;
    private final long amount;

    public PaySession(Long id, SessionImage sessionImage, SessionStatus sessionStatus, SessionDate sessionDate, int maximumStudents, long amount) {
        super(id, sessionImage, sessionStatus, sessionDate);
        assertValidMaximumStudents(maximumStudents);
        assertValidAmount(amount);
        this.amount = amount;
        this.maximumStudents = maximumStudents;
    }

    public void changeMaximumStudents(int maximumStudents) {
        assertValidMaximumStudents(maximumStudents);
        this.maximumStudents = maximumStudents;
    }

    private void assertValidMaximumStudents(int maximumStudents) {
        String errorMessage = "최대 수강생은 1명 이상으로 등록해야 합니다.";

        if (maximumStudents <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void assertValidAmount(long amount) {
        String errorMessage = "가격이 0원이 될 수 없습니다.";

        if (amount <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    @Override
    public void assertRecruit(NsUser user) {
        if (!getSessionStatus().isRecruit() || getStudents().size() == maximumStudents) {
            throw new NotRecruitException();
        }

        if (getStudents().contains(user)) {
            throw new NotRecruitException();
        }

        if (user.getChargePoint() < amount) {
            throw new LackPointException();
        }
    }

    @Override
    public Payment payResult(NsUser user) {
        return new Payment(null, getId(), user.getId(), amount);
    }
}
