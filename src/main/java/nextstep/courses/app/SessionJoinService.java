package nextstep.courses.app;

import java.util.List;

public interface SessionJoinService {
    void register(long sessionId, List<String> userIds);
}
