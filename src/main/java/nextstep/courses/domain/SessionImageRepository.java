package nextstep.courses.domain;

import nextstep.courses.domain.image.SessionImage;

import java.util.Optional;

public interface SessionImageRepository {
    int save(SessionImage image);

    Optional<SessionImage> findBySessionId(Long sessionId);
}
