package nextstep.session.domain;

import nextstep.session.dto.SessionVO;
import nextstep.session.dto.SessionUpdateBasicPropertiesVO;

public interface SessionRepository {

    long save(Session session);

    SessionVO findById(long sessionId);

    int updateSessionBasicProperties(long sessionId, SessionUpdateBasicPropertiesVO sessionUpdateBasicPropertiesVO);

    int updateCover(long sessionId, Cover newCover);
}
