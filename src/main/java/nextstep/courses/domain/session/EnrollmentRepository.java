package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.Set;

public interface EnrollmentRepository {
    Set<NsUser> findEnrolledUsersBySessionId(long sessionId);

    void save(long sessionId, NsUser user);

    void updateEnrollmentStatus(long sessionId, long userId, EnrollmentStatus status);

}