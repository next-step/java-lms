package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.SessionStatus;
import nextstep.users.domain.NsUser;

public interface Enrollment {
    void enroll(NsUser user);
    SessionStatus getStatus();
}
