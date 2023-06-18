package nextstep.courses.domain;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Sessions {
    private static final String ALREADY_SESSION = "이미 등록된 강의 입니다.";
    private static final String NOT_INFO = "등록된 정보가 없습니다.";
    private static final String NULL_EXCEPTION = "강의가 잘못 생성되었습니다.";
    private Map<Long, Session> sessionMap = new HashMap<>();

    public Sessions() {

    }
    public Sessions(List<Session> sessions) {
        if (CollectionUtils.isEmpty(sessions)) {
            throw new IllegalArgumentException("비워있는 값입니다.");
        }
        sessionMap = sessions.stream()
                .collect(Collectors.toMap(Session::getId, Function.identity(), (pre, post) -> pre));
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

    public Set<Long> getSessionIds() {
        return sessionMap.keySet();
    }

    public Map<Long, Session> getSessionMap() {
        return sessionMap;
    }

    private void putSession(Session session) {
        sessionMap.put(session.getId(), session);
    }

    private void removeSession(Session session) {
        sessionMap.remove(session.getId());
    }

    public void changeSessions() {

    }
}
