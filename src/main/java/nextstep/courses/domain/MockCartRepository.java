package nextstep.courses.domain;

import java.util.Collections;
import java.util.List;

public class MockCartRepository implements CartRepository {

    private static final List<Session> sessions = Collections.emptyList();

    @Override
    public Session findById(Long id) {
        return sessions.stream()
                .filter(s -> id == s.id())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
    }

    @Override
    public void save(Session session) {
        sessions.add(session);
    }
}
