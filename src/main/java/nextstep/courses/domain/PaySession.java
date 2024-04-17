package nextstep.courses.domain;

import nextstep.courses.domain.exception.LackPointException;
import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Set;

public class PaySession extends Session {

    private final int maximumStudents;
    private final long amount;

    public PaySession(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionDate sessionDate, int maximumStudents, long amount) {
        super(id, sessionImage, recruitStatus, sessionDate);
        assertValidMaximumStudents(maximumStudents);
        assertValidAmount(amount);
        this.amount = amount;
        this.maximumStudents = maximumStudents;
    }

    public PaySession(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionDate sessionDate, Set<NsUser> students, int maximumStudents, long amount) {
        super(id, sessionImage, recruitStatus, sessionDate, students);
        assertValidMaximumStudents(maximumStudents);
        assertValidAmount(amount);
        this.amount = amount;
        this.maximumStudents = maximumStudents;
    }

    public PaySession(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionProgressStatus sessionProgressStatus, SessionDate sessionDate, Set<NsUser> students, Set<NsUser> approveStudents, int maximumStudents, long amount) {
        super(id, sessionImage, recruitStatus, sessionProgressStatus, sessionDate, students, approveStudents);
        assertValidMaximumStudents(maximumStudents);
        assertValidAmount(amount);
        this.amount = amount;
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

    public int getMaximumStudents() {
        return maximumStudents;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public void assertSatisfiedCondition(NsUser user, Payment payment) {
        if (getStudents().size() == maximumStudents) {
            throw new NotRecruitException();
        }

        if (payment.getSessionId() == null || !payment.getSessionId().equals(getId())) {
            throw new NotRecruitException();
        }

        if (payment.getNsUserId() == null || !payment.getNsUserId().equals(user.getId())) {
            throw new NotRecruitException();
        }

        if (payment.getAmount() == null || payment.getAmount() < amount) {
            throw new LackPointException();
        }
    }
}
