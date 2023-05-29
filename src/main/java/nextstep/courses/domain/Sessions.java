package nextstep.courses.domain;

import java.util.List;

public class Sessions {

    private Long courseSessionNumber;

    private List<Session> sessions;

    public Session findSession(Long sessionId) {
        return sessions.stream()
                .filter(session -> sessionId.equals(session.id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 강의를 찾을 수 없습니다."));
    }

}
