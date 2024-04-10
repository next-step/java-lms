package nextstep.session.service;

import nextstep.session.domain.Cover;
import nextstep.session.domain.Session;
import nextstep.session.dto.SessionUpdateBasicPropertiesDto;
import nextstep.users.domain.NsUser;

public interface SessionService {

    long save(Session session);

    Session findById(long sessionId);

    int updateBasicProperties(long sessionId, SessionUpdateBasicPropertiesDto sessionUpdateDto);

    int updateCover(long sessionId, long oldCoverId, Cover newCover, NsUser requestUser);
}
