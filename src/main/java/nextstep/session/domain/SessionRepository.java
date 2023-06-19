package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {

    int save(Session session);

    Optional<Session> findById(Long id);

    List<NsUser> findAllUsersBySessionId(Long sessionId);

    int saveCoverImage(Long sessionId, SessionCoverImage image);

    Optional<SessionCoverImage> findCoverImageBySessionId(Long sessionId);

}
