package nextstep.courses.app;

import nextstep.users.domain.NsUser;

public interface SessionJoinService {
    void register(long sessionId, NsUser nsUser);

    void approve(long sessionId, NsUser nsUser);

    void reject(long sessionId, NsUser nsUser);
}
