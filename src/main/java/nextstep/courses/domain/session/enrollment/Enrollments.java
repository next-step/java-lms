package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.users.domain.NsUser;

public interface Enrollments {
    void enroll(NsUser user);
    SessionProgressStatus getProgressStatus();
    SessionRecruitmentStatus getRecruitmentStatus();
}
