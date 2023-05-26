package nextstep.courses.app;

import java.util.List;

public interface SessionJoinService {
    void register(long sessionId, List<String> userIds);

    void approve(long sessionId, List<String> userIds);

    void reject(long sessionId, List<String> userIds);
}
