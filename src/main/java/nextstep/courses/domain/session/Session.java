package nextstep.courses.domain.session;

import nextstep.courses.CanNotApplyException;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.Image.Image;
import nextstep.courses.domain.user.NsUsers;
import nextstep.users.domain.NsUser;

public abstract class Session {

    private final Image image;
    private final Period period;
    private final SessionStatus status;
    private final NsUsers users;
    private final Course course;

    public Session(Image image, Period period, SessionStatus status, Course course) {
        this.image = image;
        this.period = period;
        this.status = status;
        this.course = course;
        this.users = new NsUsers();
    }

    public NsUsers getUsers() {
        return users;
    }

    public abstract boolean isAppliable(int pay, int order) throws CanNotApplyException;

    public void apply(int pay, NsUser user) throws Exception {
        validSessionStatus();
        isAppliable(pay, users.numberOfUsers() + 1);
        users.add(user);
    }

    public void validSessionStatus() throws Exception {
        if(!status.equals(SessionStatus.RECRUIT)){
            throw new CanNotApplyException("모집 중인 강의가 아닙니다.");
        }
    }

}
