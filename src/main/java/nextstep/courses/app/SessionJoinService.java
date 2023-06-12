package nextstep.courses.app;

import nextstep.users.domain.NsUser;

public interface SessionJoinService {
    void register(long sessionId, NsUser nsUser);
}
