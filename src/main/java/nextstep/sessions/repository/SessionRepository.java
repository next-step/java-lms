package nextstep.sessions.repository;

import java.util.List;

import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.vo.CoverImage;
import nextstep.sessions.domain.data.vo.Registration;

public interface SessionRepository {

    int saveSession(Session session);

    int saveCoverImage(int sessionId, CoverImage coverImage);

    Session findSessionBySessionId(int sessionId);

    CoverImage findCoverImageBySessionId(int sessionId);

    List<Registration> findAllRegistrations(int sessionId);

    void saveRegistration(int sessionId, Registration registration);

}
