package nextstep.courses.domain.enums;

import java.util.Optional;

import nextstep.courses.domain.Session;

public interface SessionRepository {
	Optional<Session> findById(Long id);
}
