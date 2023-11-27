package nextstep.courses.domain;

import nextstep.courses.domain.strategy.EnrollmentStrategy;

public interface EnrollmentRepository {
    EnrollmentStrategy findBySessionId(long sessionId);
}
