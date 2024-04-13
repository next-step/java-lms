package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public class Sessions {

    private final Long courseId;

    private final List<Session> sessions;

    public Sessions(Long courseId, List<Session> sessions) {
        this.courseId = courseId;
        this.sessions = sessions;
    }

    public void enroll(Long userId, Long sessionId) {
        sessions.stream()
                .filter(session -> session.isEqualSessionId(sessionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 세션을 찾을 수 없습니다."))
                .enroll(userId);
    }
}
