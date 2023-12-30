package nextstep.courses.domain;

import nextstep.users.domain.NsUsers;

public interface EnrollmentRepository {
    int save(Long userId, Long sessionId);

    NsUsers findBySessionId(Long id);
}
