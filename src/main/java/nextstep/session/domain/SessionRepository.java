package nextstep.session.domain;

import nextstep.session.dto.SessionUpdateBasicPropertiesVO;

public interface SessionRepository {

    long save(Session session);

    long apply(long sessionId, Student student);

    long unapply(long sessionId, Student student);

    Session findById(long sessionId);
    Session findById2(long sessionId);

    int updateSessionBasicProperties(long sessionId, SessionUpdateBasicPropertiesVO sessionUpdateBasicPropertiesVO);

    void updateCover(long sessionId, long oldCoverId, Cover newCover);

    void delete(long sessionId);

    long addCover(long sessionId, Cover cover);
}
