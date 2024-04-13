package nextstep.session.domain;

import nextstep.session.dto.SessionUpdateBasicPropertiesVO;

public interface SessionRepository {

    long save(Session session);

    Session findById(long sessionId);

    int updateSessionBasicProperties(long sessionId, SessionUpdateBasicPropertiesVO sessionUpdateBasicPropertiesVO);
}
