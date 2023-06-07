package nextstep.courses.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Sessions {
    private static final String ALREADY_SESSION = "이미 등록된 강의 입니다.";
    private static final String NOT_INFO = "등록된 정보가 없습니다.";
    private static final String NULL_EXCEPTION = "강의가 잘못 생성되었습니다.";
    private final Map<Long, Session> sessionMap = new HashMap<>();
    public Sessions() {
    }

    public void putEntity(Session session) {
        if (Objects.isNull(session)) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }

        if (sessionMap.containsKey(session.getId())) {
            throw new IllegalArgumentException(ALREADY_SESSION);
        }
        putSession(session);
    }

    public void removeEntity(Session session) {
        if (Objects.isNull(session)) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }

        if (!sessionMap.containsKey(session.getId())) {
            throw new IllegalArgumentException(NOT_INFO);
        }
        removeSession(session);
    }

    private void putSession(Session session) {
        sessionMap.put(session.getId(), session);
    }

    private void removeSession(Session session) {
        sessionMap.remove(session.getId());
    }
}
