package nextstep.courses.domain.session;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.session.type.SessionType;
import nextstep.courses.domain.session.type.SessionStatus;
import nextstep.users.domain.NsUsers;

public class FreeSession extends Session {

    public FreeSession(Course course, Period period, Image image, NsUsers users) {
        super(course, period, image, users, SessionType.FREE);
    }

    public FreeSession(Long idx, Course course, Period period, Image image, SessionStatus status, NsUsers nsUsers) {
        super(idx, course, period, image, status, nsUsers, SessionType.FREE);
    }

    @Override
    public void assertCanEnroll() {
        return;
    }
}
