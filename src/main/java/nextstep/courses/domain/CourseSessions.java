package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Course에 할당된 Session의 모임
 * List{@literal <}Session{@literal >}의 일급 컬렉션입니다.
 */
public class CourseSessions {
    private List<Session> sessions = new ArrayList<>();

    public void add(Session session) {
        if (sessions.contains(session)) {
            throw new IllegalArgumentException("중복되어 삽입된 강의가 발견되었습니다. 시도된 강의: " + session);
        }

        sessions.add(session);
    }
}