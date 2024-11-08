package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.Set;

public interface EnrollmentRepository {
    Set<NsUser> findEnrolledUsersBySessionId(Long sessionId);

    void save(Long sessionId, NsUser user);
}