package nextstep.session.domain;

import nextstep.session.dto.SessionVO;
import nextstep.session.dto.SessionUpdateBasicPropertiesVO;

public interface SessionRepository {

    long save(SessionVO sessionVO);

    SessionVO findById(long sessionId);

    int updateSessionBasicProperties(SessionVO sessionVO, SessionUpdateBasicPropertiesVO sessionUpdateBasicPropertiesVO);

    int updateCover(SessionVO sessionVO, Cover newCover);
}
