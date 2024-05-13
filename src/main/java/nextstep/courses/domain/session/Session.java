package nextstep.courses.domain.session;

import nextstep.courses.CanNotApplyException;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.Image.Image;
import nextstep.courses.domain.user.NsUsers;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.Payments;
import nextstep.users.domain.NsUser;

public abstract class Session {

    private final long id;
    private final Image image;
    private final Period period;
    private final SessionStatus status;
    private final NsUsers users;
    private final Course course;
    private final Payments payments;

    public Session(Image image, Period period, SessionStatus status, Course course) {
        this.id = 0L;
        this.image = image;
        this.period = period;
        this.status = status;
        this.course = course;
        this.users = new NsUsers();
        this.payments = new Payments();
    }

    public NsUsers getUsers() {
        return users;
    }

    public abstract boolean isAppliable(int pay, int order) throws CanNotApplyException;

    public void apply(int pay, NsUser user) throws Exception {
        validSessionStatus();
        isAppliable(pay, users.numberOfUsers() + 1);
        users.add(user);
        payments.add(toPayment(pay, user));
    }

    public Payment toPayment(int pay, NsUser user) {
        return new Payment("0", id, user.getId(), (long) pay);
    }

    private void validSessionStatus() throws Exception {
        if (!status.equals(SessionStatus.RECRUIT)) {
            throw new CanNotApplyException("모집 중인 강의가 아닙니다.");
        }
    }

}
