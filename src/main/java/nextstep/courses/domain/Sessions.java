package nextstep.courses.domain;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Sessions {
    private static final String ALREADY_SESSION = "이미 등록된 강의 입니다.";
    private static final String NOT_INFO = "등록된 정보가 없습니다.";
    private static final String NULL_EXCEPTION = "강의가 잘못 생성되었습니다.";
    private Map<Long, SessionUser> sessionMap = new HashMap<>();

    public Sessions() {

    }

    public Sessions(List<SessionUser> sessionUsers) {
        if (CollectionUtils.isEmpty(sessionUsers)) {
            throw new IllegalArgumentException("비워있는 값입니다.");
        }
        sessionMap = sessionUsers.stream()
                .collect(Collectors.toMap(SessionUser::getNsUserId, Function.identity(), (pre, post) -> pre));
    }

    public void putEntity(SessionUser sessionUser) {
        if (Objects.isNull(sessionUser)) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }

        if (sessionMap.containsKey(sessionUser.getId())) {
            throw new IllegalArgumentException(ALREADY_SESSION);
        }
        putSession(sessionUser);
    }

    public void removeEntity(SessionUser sessionUser) {
        if (Objects.isNull(sessionUser)) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }

        if (!sessionMap.containsKey(sessionUser.getId())) {
            throw new IllegalArgumentException(NOT_INFO);
        }
        removeSession(sessionUser);
    }

    public List<Long> getSessionIds() {
        return sessionMap.values()
                .stream()
                .map(SessionUser::getSessionId)
                .collect(Collectors.toList());
    }

    public int getSize() {
        return sessionMap.size();
    }

    private void putSession(SessionUser sessionUser) {
        sessionMap.put(sessionUser.getId(), sessionUser);
    }

    private void removeSession(SessionUser sessionUser) {
        sessionMap.remove(sessionUser.getId());
    }

}
