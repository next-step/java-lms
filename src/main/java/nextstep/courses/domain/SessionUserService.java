package nextstep.courses.domain;

import java.util.List;

public interface SessionUserService {
    void enroll(long sessionId, String user);

    void approve(long sessionId, List<String> users);

    void reject(long sessionId, List<String> users);
}
