package nextstep.courses.domain.session.enrollment;

import nextstep.users.domain.NsUser;

public interface Enrollment {
    void enroll(NsUser user);
    boolean isFull();
    boolean hasEnrolledUser(NsUser user);
} 