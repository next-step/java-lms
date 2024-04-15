package nextstep.courses.domain.session;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.session.type.SessionType;
import nextstep.courses.domain.session.type.SessionStatus;
import nextstep.courses.domain.session.user.SessionUsers;
import nextstep.users.domain.NsUsers;

import java.util.List;

public class FreeSession extends Session {

    public FreeSession(String title, Course course, Period period, List<Image> images, SessionUsers users) {
        super(course, title, period, images, users, SessionType.FREE);
    }

    public FreeSession(Long idx, String title, Course course, Period period, List<Image> images, SessionStatus status, SessionUsers sessionUsers, Long creatorId) {
        super(idx, title, course, period, images, status, sessionUsers, SessionType.FREE,creatorId);
    }

    @Override
    public void assertCanEnroll() {
        return;
    }
}
