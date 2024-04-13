package nextstep.session.domain;

import nextstep.common.domain.DeleteHistory;
import nextstep.users.domain.NsUser;

public interface CoverService {

    Cover findById(long coverId);

    Cover findBySessionId(long sessionId);

    DeleteHistory delete(long coverId, NsUser requestUser);

    Long save(Cover cover);
}
